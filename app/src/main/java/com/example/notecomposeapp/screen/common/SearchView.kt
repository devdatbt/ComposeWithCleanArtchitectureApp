package com.example.notecomposeapp.screen.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
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