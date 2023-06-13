package com.example.notecomposeapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notecomposeapp.R
import com.example.notecomposeapp.ui.theme.MyAppTheme


@Composable
fun TopBarView(
    onClickTopBar: () -> Unit,
    icon: ImageVector = Icons.Filled.Home,
    title: String = stringResource(id = R.string.tv_title_note_app)
) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onClickTopBar.invoke()
                    },
                //painter = painterResource(id = R.drawable.ic_vector),
                imageVector = icon, contentDescription = "",
            )
            Text(text = title, style = MyAppTheme.typography.title)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}