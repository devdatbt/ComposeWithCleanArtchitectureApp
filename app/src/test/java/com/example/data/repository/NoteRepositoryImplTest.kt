package com.example.data.repository

import com.example.data.datasource.local.NoteDao
import com.example.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
}