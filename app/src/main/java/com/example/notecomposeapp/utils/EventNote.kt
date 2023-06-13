package com.example.notecomposeapp.utils

import com.example.domain.model.Note

sealed class EventNote {
    data class EventDeleteNote(val note: Note) : EventNote()
    data class EventInsertNote(val note: Note) : EventNote()
    data class EventUpdateNote(val title: String, val content: String, val timestamp: Long) :
        EventNote()
}
