package com.example.notecomposeapp.screen.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoSnackBar() {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(content = { paddingContent ->
        Box(
            modifier = Modifier
                .padding(paddingContent)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    coroutineScope.launch {

                        val snackBarResult = snackBarHostState.showSnackbar(
                            message = "Snackbar is here",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )
                        when (snackBarResult) {
                            SnackbarResult.ActionPerformed -> {
                                Log.d("Snackbar", "Action Performed")
                            }

                            else -> {
                                Log.d("Snackbar", "Snackbar dismissed")
                            }
                        }
                    }

                }) {
                    Text(text = "Show Snack Bar", color = Color.White)
                }
            }
        }
    }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) })
}