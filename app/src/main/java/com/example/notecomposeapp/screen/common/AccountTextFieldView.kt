package com.example.notecomposeapp.screen.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.notecomposeapp.theme.MyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTextFieldView(
    isPasswordType: Boolean,
    icon: ImageVector,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = icon, contentDescription = "User Icon"
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MyAppTheme.color.blackColor,
            unfocusedBorderColor = MyAppTheme.color.grayColor,
            focusedLabelColor = MyAppTheme.color.blackColor
        ),
        visualTransformation = if (passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = 1,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = "Enter here...") },
        keyboardOptions =
        KeyboardOptions(keyboardType = if (isPasswordType) KeyboardType.Password else KeyboardType.Email),
        trailingIcon = {
            if (isPasswordType) {
                val iconImage =
                    if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            }
        }
    )
}

@Composable
@Preview
fun PreviewLoginScreen() {
    val loginValue by rememberSaveable {
        mutableStateOf("")
    }
    AccountTextFieldView(false, Icons.Default.Password, "label", loginValue) {

    }
}