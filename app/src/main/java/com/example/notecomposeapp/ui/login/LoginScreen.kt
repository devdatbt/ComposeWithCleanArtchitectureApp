package com.example.notecomposeapp.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notecomposeapp.ext.clearAndNavigate
import com.example.notecomposeapp.ui.ROUTE_HOME_NOTE
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.theme.Purple700
import com.example.notecomposeapp.ui.common.TextFieldLogin
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState

    Scaffold(
        backgroundColor = MyAppTheme.color.backgroundApp,
        scaffoldState = scaffoldState,
    ) { paddingContent ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingContent)
                .padding(20.dp)
        ) {

            Text(
                text = "Login", color = MyAppTheme.color.blackColor,
                style = MyAppTheme.typography.largeTitle,
            )

            TextFieldLogin(Icons.Default.SupervisedUserCircle, "Enter email.", uiState.email) {
                viewModel.onEmailChange(it)
            }

            TextFieldLogin(Icons.Default.Password, "Enter password.", uiState.password) {
                viewModel.onPassChange(it)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        viewModel.authenticationEmail { isLoginSuccess ->
                            if (isLoginSuccess) {
                                navController.clearAndNavigate(ROUTE_HOME_NOTE)
                            } else {
                                coroutineScope.launch {
                                    // using the `coroutineScope` to `launch` showing the snackbar
                                    // taking the `snackbarHostState` from the attached `scaffoldState`
                                    val snackbarResult =
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "This is your message",
                                            actionLabel = "Do something."
                                        )
                                    when (snackbarResult) {
                                        SnackbarResult.Dismissed -> Log.d(
                                            "SnackbarDemo",
                                            "Dismissed"
                                        )
                                        SnackbarResult.ActionPerformed -> Log.d(
                                            "SnackbarDemo",
                                            "Snackbar's button clicked"
                                        )
                                    }
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            ClickableText(
                text = AnnotatedString("Sign up here"),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = { },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Purple700
                )
            )
        }
    }

}

@Composable
private fun DraggableText() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(Color.Blue)
                .size(50.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}

@Composable
@Preview
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}