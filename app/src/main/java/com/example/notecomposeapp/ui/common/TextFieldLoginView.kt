package com.example.notecomposeapp.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.notecomposeapp.theme.MyAppTheme

@Composable
fun TextFieldLogin(
    isPasswordType: Boolean,
    icon: ImageVector,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = icon, contentDescription = "User Icon"
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MyAppTheme.color.blackColor,
            unfocusedBorderColor = MyAppTheme.color.greenColor,
            focusedLabelColor = MyAppTheme.color.greenColor
        ),
        visualTransformation = if (isPasswordType) PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = 1,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = "Enter here...") })
}

@Composable
@Preview
fun PreviewLoginScreen() {
    val loginValue by rememberSaveable {
        mutableStateOf("")
    }
    TextFieldLogin(false, Icons.Default.Password, "label", loginValue) {

    }
}