package com.example.notecomposeapp.screen.note

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.Note
import com.example.notecomposeapp.R
import com.example.notecomposeapp.extension.popUp
import com.example.notecomposeapp.screen.common.NoteTextFieldView
import com.example.notecomposeapp.screen.common.TopBarView
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.theme.listColorGradient
import com.example.notecomposeapp.utils.EventNote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateNoteScreen(
    titleId: Int? = null,
    note: Note? = null,
    navHostController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    var title by remember {
        mutableStateOf(note?.title ?: "")
    }

    var content by remember {
        mutableStateOf(note?.content ?: "")
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopBarView(
                iconLeft = Icons.Filled.ArrowBack,
                title = titleId?.let { stringResource(id = it) },
                onClickLeftTopBar = {
                    navHostController.popUp()
                })

            NoteTextFieldView(
                value = title,
                onValueChange = { title = it },
                label = stringResource(id = R.string.tv_title)
            )

            Spacer(modifier = Modifier.height(20.dp))

            NoteTextFieldView(
                value = content,
                onValueChange = { content = it },
                label = stringResource(id = R.string.tv_content),
                modifier = Modifier.height(200.dp),
                maxLines = 20
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(modifier = Modifier.background(
                brush = Brush.horizontalGradient(listColorGradient),
                shape = ButtonDefaults.shape
            ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {
                    if (title.isBlank() || content.isBlank()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.tv_pls_enter_field),
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }
                    // handle update note or add new note
                    if (note != null) {
                        viewModel.onEventNote(
                            EventNote.EventUpdateNote(
                                title, content, note.timestamp
                            )
                        )
                    } else {
                        viewModel.onEventNote(
                            EventNote.EventInsertNote(
                                Note(
                                    title, content, System.currentTimeMillis()
                                )
                            )
                        )
                    }
                    navHostController.popUp()
                }) {
                Text(
                    text = stringResource(id = R.string.tv_save),
                    color = MyAppTheme.color.whiteColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddNoteScreen() {
    //AddUpdateNoteScreen()
}