package com.example.notecomposeapp.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Note
import com.example.notecomposeapp.theme.MyAppTheme
import com.example.notecomposeapp.utils.Constant.DEFAULT_STRING

@Composable
fun ItemNoteScreen(
    note: Note,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    onDeleteItemClick: (Note) -> Unit
) {
    Surface(color = MyAppTheme.color.greenColor,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke()
            }) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(8f)) {
                Text(
                    color = MyAppTheme.color.whiteColor,
                    text = note.title ?: DEFAULT_STRING,
                    style = MyAppTheme.typography.title,
                )
                Text(
                    color = MyAppTheme.color.whiteColor,
                    style = MyAppTheme.typography.body,
                    text = note.content ?: DEFAULT_STRING,
                )
            }

            Icon(tint = MyAppTheme.color.whiteColor,
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete item note",
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable {
                        onDeleteItemClick.invoke(note)
                    })
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
@Preview
fun PreviewItemNoteScreen() {
    val note = Note(title = "Title", "Content note infomation", timestamp = 152563716)
    ItemNoteScreen(note, onItemClick = {

    }, onDeleteItemClick = {

    })
}