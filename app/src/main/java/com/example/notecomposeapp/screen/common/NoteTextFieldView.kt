package com.example.notecomposeapp.screen.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notecomposeapp.theme.MyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTextFieldView(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 3
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = MyAppTheme.color.lightBlueColor
            )
        },
        maxLines = maxLines,
        modifier = modifier,
        colors = outLineTextFieldColors()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun outLineTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MyAppTheme.color.grayColor,
        unfocusedBorderColor = MyAppTheme.color.lightBlueColor
    )
}