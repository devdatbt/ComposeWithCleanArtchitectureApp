package com.example.notecomposeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.notecomposeapp.R
import com.example.notecomposeapp.ui.theme.MyAppTheme
import com.example.notecomposeapp.utils.EventNote
import com.example.notecomposeapp.utils.convertCurrency
import com.example.notecomposeapp.utils.objectToJson
import com.example.notecomposeapp.viewmodel.NoteViewModel

@Composable
fun NoteHomeScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: NoteViewModel = hiltViewModel()
) {
    val notes = viewModel.listNoteStateIn.collectAsStateWithLifecycle(emptyList())
    val currency = viewModel.statusGetCurrencyApi.observeAsState()
    val scaffoldState = rememberScaffoldState()
    // remember value after change state
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        backgroundColor = MyAppTheme.color.backgroundApp,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("$ROUTE_UPDATE_NOTE/{}")
            }, content = {
                Icon(
                    tint = MyAppTheme.color.whiteColor,
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }, backgroundColor = MyAppTheme.color.greenColor)
        },
        scaffoldState = scaffoldState,
    ) { paddingContent ->
        Box() {
            Column(
                modifier = Modifier
                    .padding(paddingContent)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                // header
                TopBarView(
                    onClickTopBar = {},
                    icon = Icons.Filled.Home,
                    title = stringResource(id = R.string.tv_title_note_app)
                )
                // show currency
                Text(
                    modifier = Modifier.align(alignment = Alignment.End),
                    color = MyAppTheme.color.blackColor,
                    style = MyAppTheme.typography.body,
                    text = "1 USD = ${currency.value?.data?.usdVnd?.convertCurrency() ?: "..."} VND"
                )
                Spacer(modifier = Modifier.height(10.dp))
                SearchView(searchValue) {
                    searchValue = it
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.tv_lists_note),
                    style = MyAppTheme.typography.subTitle
                )
                LazyColumn(modifier = Modifier.wrapContentHeight()) {
                    val listNote = viewModel.searchListNoteWith(searchValue, listFilter = notes.value)
                    items(listNote) { note ->
                        ItemNoteScreen(note = note,
                            modifier = Modifier.fillMaxWidth(),
                            onItemClick = {
                                val noteString = note.objectToJson()
                                navController.navigate("$ROUTE_UPDATE_NOTE/$noteString")
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
fun SearchView(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Email Icon"
            )
        },
        maxLines = 1,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Search") },
        placeholder = { Text(text = "Input...") })
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