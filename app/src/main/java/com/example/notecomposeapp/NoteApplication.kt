package com.example.notecomposeapp

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import com.google.firebase.FirebaseApp

@HiltAndroidApp
class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}