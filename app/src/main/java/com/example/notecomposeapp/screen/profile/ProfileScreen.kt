package com.example.notecomposeapp.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.notecomposeapp.theme.MyAppTheme

@Composable
fun AccountScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Surface {
        ConstraintLayout(
            modifier = modifier
                .wrapContentHeight()
                .background(color = Color(0xFF1E88E5))
        ) {
            val (imageProfile, username, birthday, contentColumn) = createRefs()
            val topImgGuideline = createGuidelineFromTop(0.20f)
            Column(modifier = Modifier
                .constrainAs(contentColumn) {
                    top.linkTo(topImgGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(color = Color.White)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = ProfileSettingItems.SettingItem.colorGradientBackground
                            )
                        )
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        imageVector = ProfileSettingItems.SettingItem.iconLeft,
                        contentDescription = "",
                    )
                    Text(text = ProfileSettingItems.SettingItem.title)
                    Image(
                        imageVector = ProfileSettingItems.SettingItem.iconRight,
                        contentDescription = "",
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .background(
                            Brush.horizontalGradient(
                                colors = ProfileSettingItems.NotificationItem.colorGradientBackground
                            )
                        )
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        imageVector = ProfileSettingItems.NotificationItem.iconLeft,
                        contentDescription = "",
                    )
                    Text(text = ProfileSettingItems.NotificationItem.title)
                    Image(
                        imageVector = ProfileSettingItems.NotificationItem.iconRight,
                        contentDescription = "",
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .background(
                            Brush.horizontalGradient(
                                colors = ProfileSettingItems.LogoutItem.colorGradientBackground
                            )
                        )
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        imageVector = ProfileSettingItems.LogoutItem.iconLeft,
                        contentDescription = "",
                    )
                    Text(text = ProfileSettingItems.LogoutItem.title)
                    Image(
                        imageVector = ProfileSettingItems.LogoutItem.iconRight,
                        contentDescription = "",
                    )
                }
            }
            Image(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .constrainAs(imageProfile) {
                        bottom.linkTo(contentColumn.top)
                        start.linkTo(parent.start)
                    },
                colorFilter = ColorFilter.tint(color = Color.White)
            )
            Text(
                text = "Email@gmail.com",
                style = MyAppTheme.typography.subTitle.copy(Color.White),
                modifier = Modifier
                    .constrainAs(username) {
                        top.linkTo(imageProfile.top)
                        start.linkTo(imageProfile.end)
                    }
                    .padding(top = 20.dp)
            )
            Text(
                text = "1997",
                style = MyAppTheme.typography.body.copy(Color.White),
                modifier = Modifier.constrainAs(birthday) {
                    top.linkTo(username.bottom)
                    start.linkTo(imageProfile.end)
                })
        }
    }
}

@Preview
@Composable
fun PreviewAccountScreen() {
    AccountScreen(navHostController = NavHostController(LocalContext.current), Modifier)
}