package com.example.notecomposeapp.screen.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notecomposeapp.R
import com.example.notecomposeapp.theme.MyAppTheme


@Composable
fun TopBarView(
    onClickLeftTopBar: (() -> Unit)? = null,
    onClickRightTopBar: (() -> Unit)? = null,
    iconLeft: ImageVector? = null,
    title: String? = null,
    iconRight: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        iconLeft?.let {
            Icon(
                tint = MyAppTheme.color.lightBlueColor,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onClickLeftTopBar?.invoke()
                    },
                imageVector = it, contentDescription = "",
            )
        }
        title?.let { Text(text = it, style = MyAppTheme.typography.title) }
        iconRight?.let {
            Icon(
                tint = MyAppTheme.color.lightBlueColor,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onClickRightTopBar?.invoke()
                    },
                imageVector = it, contentDescription = "",
            )
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}