package com.example.notecomposeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Currency
import com.example.domain.model.Note
import com.example.notecomposeapp.usecase.AppUseCase
import com.example.notecomposeapp.utils.EventNote
import com.example.notecomposeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val appUseCase: AppUseCase) : BaseViewModel() {
    private val LOG_TAG = NoteViewModel::class.java.simpleName
    private val _statusGetCurrency: MutableLiveData<Resource<Currency>> = MutableLiveData()
    val statusGetCurrencyApi: LiveData<Resource<Currency>>
        get() = _statusGetCurrency

    private var _listNoteState = MutableStateFlow<List<Note>>(emptyList())
    val listNoteState = _listNoteState.asStateFlow()


    init {
        getCurrencyFromServer()
        getAllNoteFromDB()
    }

    private fun getAllNoteFromDB() {
        launchDataLoad {
            appUseCase.getNoteListsUseCase.invoke()
                .catch {
                    this.emit(emptyList())
                }
                .onCompletion { cause ->
                    if (cause == null)
                        Log.e(LOG_TAG, "Success ${Thread.currentThread().name}")
                    else
                        Log.e(LOG_TAG, "Failed: $cause")
                }
                .collect {
                    _listNoteState.value = it
                }
        }
    }

    private fun searchNoteWith(keyWord: String) {
        launchDataLoad {
            _listNoteState.value.asFlow()
                .filter {
                    it.title.equals(keyWord)
                }
                .flowOn(Dispatchers.Default)
                .launchIn(viewModelScope)
        }
    }

    private fun insertNote(note: Note) {
        launchDataLoad {
            appUseCase.addNoteUseCase.invoke(note)
        }
    }

    private fun deleteNote(note: Note) {
        launchDataLoad {
            appUseCase.deleteNoteUseCase.invoke(note)
        }
    }

    private fun updateNote(title: String, content: String, time: Long) {
        launchDataLoad {
            appUseCase.updateNoteUseCase.invoke(title = title, content = content, time = time)
        }
    }

    private fun getCurrencyFromServer() {
        _statusGetCurrency.value = Resource.loading(null)
        launchDataLoad {
            appUseCase.getCurrencyUseCase.invoke().let {
                if (it.success)
                    _statusGetCurrency.value = Resource.success(it)
                else
                    _statusGetCurrency.value = Resource.error(null, "Error success: ${it.success}")
            }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block.invoke()
            } catch (error: Throwable) {
                error.printStackTrace()
            }
        }
    }

    fun onEventNote(event: EventNote) {
        when (event) {
            is EventNote.EventInsertNote -> {
                insertNote(event.note)
            }
            is EventNote.EventUpdateNote -> {
                updateNote(event.title, event.content, event.timestamp)
            }
            is EventNote.EventDeleteNote -> {
                deleteNote(event.note)
            }
        }
    }
}