package com.example.notecomposeapp.ui.note

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.Note
import com.example.notecomposeapp.R
import com.example.notecomposeapp.ext.popUp
import com.example.notecomposeapp.ui.common.TopBarView
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.utils.EventNote

@Composable
fun AddUpdateNoteScreen(
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
                icon = Icons.Filled.ArrowBack,
                title = stringResource(id = R.string.tv_update_note_app),
                onClickTopBar = {
                    navHostController.popUp()
                })

            OutlinedTextField(maxLines = 3, value = title, onValueChange = {
                title = it
            }, label = {
                Text(
                    text = stringResource(id = R.string.tv_title),
                    color = MyAppTheme.color.backgroundCard
                )
            }, colors = outLineTextFieldColors())
            Spacer(
                modifier = Modifier.height(20.dp)
            )

            OutlinedTextField(
                modifier = Modifier.height(200.dp),
                maxLines = 20,
                value = content,
                onValueChange = {
                    content = it
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.tv_content),
                        color = MyAppTheme.color.backgroundCard
                    )
                },
                colors = outLineTextFieldColors()
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(colors = ButtonDefaults.buttonColors(
                backgroundColor = MyAppTheme.color.greenColor
            ), onClick = {
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

@Composable
fun outLineTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MyAppTheme.color.backgroundCard,
        unfocusedBorderColor = MyAppTheme.color.backgroundCard
    )
}

@Preview
@Composable
fun PreviewAddNoteScreen() {
    //AddUpdateNoteScreen()
}