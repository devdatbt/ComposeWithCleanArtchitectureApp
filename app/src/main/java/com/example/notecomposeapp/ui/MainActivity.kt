package com.example.notecomposeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Note
import com.example.notecomposeapp.ui.login.LoginScreen
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.ui.note.AddUpdateNoteScreen
import com.example.notecomposeapp.ui.note.NoteHomeScreen
import com.example.notecomposeapp.utils.jsonToObject
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme({
                NoteNavigationApp(auth)
            }, isDark = false)
        }
    }
}

const val ROUTE_UPDATE_NOTE = "add_note"
const val ROUTE_HOME_NOTE = "home_note"
const val PARAM_ITEM_NOTE = "item_note"
const val ROUTE_LOGIN_NOTE = "login_note"

@Composable
fun NoteNavigationApp(auth: FirebaseAuth) {
    // Check if user is signed in (non-null) and update UI accordingly.
    var startDestination = ROUTE_LOGIN_NOTE
    if (auth.currentUser != null) {
        Log.d("Datbt","auth.currentUser != null")
        startDestination = ROUTE_HOME_NOTE
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        this.noteNavGraph(navController)
    }
}

fun NavGraphBuilder.noteNavGraph(navController: NavHostController) {

    // login
    composable(ROUTE_LOGIN_NOTE) {
        LoginScreen(navController)
    }

    //update
    composable(
        "$ROUTE_UPDATE_NOTE/{$PARAM_ITEM_NOTE}",
        arguments = listOf(navArgument(PARAM_ITEM_NOTE) {
            type = NavType.StringType
        })
    ) { navBackStack ->
        val itemNote = navBackStack.arguments?.getString(PARAM_ITEM_NOTE)
        if (itemNote.equals("{}")) {
            AddUpdateNoteScreen(note = null, navHostController = navController)
        } else {
            val note: Note = itemNote!!.jsonToObject(Note::class.java)
            AddUpdateNoteScreen(note = note, navHostController = navController)
        }
    }

    //hone
    composable(ROUTE_HOME_NOTE) {
        NoteHomeScreen(navController)
    }
}