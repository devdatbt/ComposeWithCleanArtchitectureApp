package com.example.notecomposeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.model.Note
import com.example.notecomposeapp.ui.theme.MyAppTheme
import com.example.notecomposeapp.utils.jsonToObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme({
                NoteNavigationApp()
            }, isDark = false)
        }
    }
}
const val ROUTE_UPDATE_NOTE = "add_note"
@Composable
fun NoteNavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_note") {
        composable("home_note") {
            NoteHomeScreen(navController)
        }
        composable("$ROUTE_UPDATE_NOTE/{item_note}", arguments = listOf(navArgument("item_note") {
            type = NavType.StringType
        })) { navBackStack ->
            val itemNote = navBackStack.arguments?.getString("item_note")
            if (itemNote.equals("{}")) {
                AddUpdateNoteScreen(navController = navController, note = null)
            } else {
                val note: Note = itemNote!!.jsonToObject(Note::class.java)
                AddUpdateNoteScreen(navController = navController, note = note)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteNavigationApp()
}