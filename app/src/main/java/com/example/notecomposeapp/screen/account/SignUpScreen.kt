package com.example.notecomposeapp.screen.account

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notecomposeapp.screen.main.ROUTE_HOME_NOTE
import com.example.notecomposeapp.extension.clearAndNavigate
import com.example.notecomposeapp.screen.common.AccountTextFieldView
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.utils.Constant.SIGNUP_TITLE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
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
                    text = SIGNUP_TITLE, color = MyAppTheme.color.lightBlueColor,
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

                Spacer(modifier = Modifier.height(10.dp))
                AccountTextFieldView(
                    isPasswordType = true,
                    icon = Icons.Default.Key,
                    label = "Confirm password.",
                    value = uiState.confirmPassword
                ) {
                    accountViewModel.onConfirmPassChange(it)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = uiState.isAcceptPolicy, onCheckedChange = {
                        accountViewModel.onSetIsAcceptPolicy(it)
                    })
                    Text(
                        "By continuing you accept our Privacy Policy and Term of Use",
                        style = TextStyle(fontSize = 10.sp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            accountViewModel.createAccount { isSignUpSuccess ->
                                if (AccountViewModel.Status.SIGNUP_OK == isSignUpSuccess) {
                                    navController.clearAndNavigate(ROUTE_HOME_NOTE)
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
                            text = SIGNUP_TITLE,
                            style = TextStyle(color = MyAppTheme.color.whiteColor)
                        )
                    }
                }
            }
        }
    }
}