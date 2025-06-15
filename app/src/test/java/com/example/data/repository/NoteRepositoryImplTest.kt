package com.example.data.repository

import android.util.Log
import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteEntity
import com.example.data.models.CurrencyDataDto
import com.example.data.models.QuotesDto
import com.example.domain.model.Note
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class NoteRepositoryImplTest {
    @Mock
    lateinit var noteRepositoryImpl: NoteRepositoryImpl

    @Mock
    lateinit var noteDao: NoteDao

    @Before
    fun setUp() {
        noteRepositoryImpl = NoteRepositoryImpl(noteDao)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testInsertNote() = runBlocking {
        noteRepositoryImpl.insertNote(Note(title = "title", content = "content", 12343243))
    }

    @Test
    fun testGetAllNotes() = runBlocking {
        `when`(noteDao.getNoteLists()).thenReturn(
            flow {
                emit(getNoteDataList())
            }.flowOn(Dispatchers.IO)
                .conflate()
        )

       val allNotes = noteRepositoryImpl.getAllNotes.first()
        assertNotNull(allNotes)
    }

    private fun getNoteDataList(): List<NoteEntity> {
        return arrayListOf(NoteEntity(title = "Title", content = "Content", timestamp = 123098712))
    }
}