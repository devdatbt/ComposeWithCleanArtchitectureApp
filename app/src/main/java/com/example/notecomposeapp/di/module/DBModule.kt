package com.example.notecomposeapp.di.module

import android.content.Context
import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase =
        NoteDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

}