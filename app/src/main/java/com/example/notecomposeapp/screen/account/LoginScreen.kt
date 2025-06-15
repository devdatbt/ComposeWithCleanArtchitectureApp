package com.example.notecomposeapp.screen.account

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
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
import com.example.notecomposeapp.screen.main.ROUTE_MAIN
import com.example.notecomposeapp.screen.main.ROUTE_SIGNUP_NOTE
import com.example.notecomposeapp.extension.clearAndNavigate
import com.example.notecomposeapp.screen.common.AccountTextFieldView
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.utils.Constant.LOGIN_TITLE
import com.example.notecomposeapp.utils.Constant.SIGNUP_TITLE
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by accountViewModel.uiState

    Scaffold(
        contentColor = MyAppTheme.color.grayColor
    ) { paddingContent ->

        Box(
            modifier = Modifier
                .padding(paddingContent)
                .padding(30.dp, 100.dp)
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .border(BorderStroke(1.dp, MyAppTheme.color.whiteColor))
                .background(MyAppTheme.color.whiteColor)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                Text(
                    text = LOGIN_TITLE, color = MyAppTheme.color.lightBlueColor,
                    style = MyAppTheme.typography.largeTitle,
                    modifier = Modifier.padding(horizontal = 10.dp, 20.dp)
                )

                AccountTextFieldView(
                    isPasswordType = false,
                    icon = Icons.Default.Person,
                    label = "Enter email.",
                    value = uiState.email
                ) {
                    accountViewModel.onEmailChange(it)
                }

                Spacer(modifier = Modifier.height(10.dp))
                AccountTextFieldView(
                    isPasswordType = true,
                    icon = Icons.Default.Key,
                    label = "Enter password.",
                    value = uiState.password
                ) {
                    accountViewModel.onPassChange(it)
                }

                Spacer(modifier = Modifier.height(40.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            accountViewModel.authenticationEmail { isLoginSuccess ->
                                if (isLoginSuccess) {
                                    navController.clearAndNavigate(ROUTE_MAIN)
                                } else {
                                    Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(MyAppTheme.color.lightBlueColor)
                    ) {
                        Text(
                            text = LOGIN_TITLE,
                            style = TextStyle(color = MyAppTheme.color.whiteColor)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("Don't have an account? $SIGNUP_TITLE"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    onClick = {
                        navController.navigate(ROUTE_SIGNUP_NOTE)
                    },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.Underline,
                        color = MyAppTheme.color.blackColor
                    )
                )
            }
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