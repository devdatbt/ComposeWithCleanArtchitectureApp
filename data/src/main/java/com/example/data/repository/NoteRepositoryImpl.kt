package com.example.data.repository

import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteEntity
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    private val dispatcherDefault = Dispatchers.IO

    override val getAllNotes: Flow<List<Note>>
        get() = noteDao.getNoteLists()
            .map {
                it.map { itemNote ->
                    itemNote.toNote()
                }
            }.flowOn(dispatcherDefault)

    override suspend fun insertNote(note: Note) = withContext(dispatcherDefault) {
        val noteEntity = NoteEntity.fromNote(note)
        noteDao.insertNote(noteEntity)
    }

    override suspend fun updateNote(title: String, content: String, time: Long) =
        withContext(dispatcherDefault) {
            noteDao.updateNote(title = title, content = content, time = time)
        }

    override suspend fun deleteNote(note: Note) = withContext(dispatcherDefault) {
        val noteEntity = NoteEntity.fromNote(note)
        noteDao.deleteNote(noteEntity)
    }

    override fun getNoteWithId(id: Long): Flow<Note> {
        return noteDao.getNoteWithId(id)
            .map {
                it.toNote()
            }
            .flowOn(dispatcherDefault)
    }
}