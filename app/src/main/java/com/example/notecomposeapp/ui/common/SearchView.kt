package com.example.notecomposeapp.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
fun preview(){
    SearchView(value = "test", onValueChange = {

    })
}