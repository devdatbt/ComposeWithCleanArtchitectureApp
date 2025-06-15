package com.example.notecomposeapp.screen.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.notecomposeapp.R
import com.example.notecomposeapp.extension.clearAndNavigate
import com.example.notecomposeapp.screen.common.SearchView
import com.example.notecomposeapp.screen.common.TopBarView
import com.example.notecomposeapp.screen.main.ROUTE_LOGIN_NOTE
import com.example.notecomposeapp.screen.main.ROUTE_UPDATE_NOTE
import com.example.notecomposeapp.utils.EventNote
import com.example.notecomposeapp.utils.objectToJson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteHomeScreen(
    navHostController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val notes = viewModel.listNoteStateIn.collectAsStateWithLifecycle(emptyList())
    val currency = viewModel.statusGetCurrencyApi.collectAsStateWithLifecycle()
    // remember value after change state
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(modifier = modifier) { paddingContent ->
        Box() {
            Column(
                modifier = Modifier
                    .padding(paddingContent)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                // header
                TopBarView(
                    onClickLeftTopBar = {
                        viewModel.signOut {
                            navHostController.clearAndNavigate(ROUTE_LOGIN_NOTE)
                        }
                    },
                    iconLeft = Icons.Filled.Logout,
                    title = stringResource(id = R.string.tv_title_note_app),
                    iconRight = Icons.Filled.Add,
                    onClickRightTopBar = {
                        navHostController.navigate("$ROUTE_UPDATE_NOTE/{}")
                    }
                )
                // show currency
//                Text(
//                    modifier = Modifier.align(alignment = Alignment.End),
//                    color = MyAppTheme.color.blackColor,
//                    style = MyAppTheme.typography.body,
//                    text = "1 USD = ${currency.value?.data?.usdVnd?.convertCurrency() ?: "..."} VND"
//                )
                Spacer(modifier = Modifier.height(10.dp))
                SearchView(searchValue) {
                    searchValue = it
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(modifier = Modifier.wrapContentHeight()) {
                    val listNote =
                        viewModel.searchListNoteWith(searchValue, listFilter = notes.value)
                    items(listNote) { note ->
                        ItemNoteScreen(note = note,
                            modifier = Modifier.fillMaxWidth(),
                            onItemClick = {
                                val noteString = note.objectToJson()
                                navHostController.navigate("$ROUTE_UPDATE_NOTE/$noteString")
                            },
                            onDeleteItemClick = {
                                viewModel.onEventNote(EventNote.EventDeleteNote(it))
                            })
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewNoteHomeScreen() {
    // remember value after change state
    var searchValue by remember {
        mutableStateOf("")
    }
    SearchView(value = searchValue, onValueChange = {
        searchValue = it
    })
}