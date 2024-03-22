package com.example.notecomposeapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


data class AppTypography(
    val largeTitle: TextStyle = TextStyle.Default,
    val title: TextStyle = TextStyle.Default,
    val subTitle: TextStyle = TextStyle.Default,
    val body: TextStyle = TextStyle.Default,
)

data class AppColor(
    val blackColor: Color = Color.Unspecified,
    val backgroundCard: Color = Color.Unspecified,
    val backgroundApp: Color = Color.Unspecified,
    val whiteColor: Color = Color.Unspecified,
    val greenColor: Color = Color.Unspecified,
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography()
}

val LocalAppColor = staticCompositionLocalOf {
    AppColor()
}

@Composable
fun MyAppTheme(
    content: @Composable () -> Unit, isDark: Boolean = isSystemInDarkTheme()
) {
    val typography = AppTypography(
        largeTitle = TextStyle(
            fontFamily = FontFamily.Monospace, fontSize = 32.sp, fontWeight = FontWeight.Bold
        ), title = TextStyle(
            fontFamily = FontFamily.Monospace, fontSize = 24.sp, fontWeight = FontWeight.Bold
        ), subTitle = TextStyle(
            fontFamily = FontFamily.Monospace, fontSize = 18.sp, fontWeight = FontWeight.Bold
        ), body = TextStyle(
            fontFamily = FontFamily.Monospace, fontSize = 16.sp, fontWeight = FontWeight.Normal
        )
    )

    val color = if (isDark) AppColor(
        blackColor = Color(0xFF000000),
        greenColor = Color(0xFF82B70B),
        whiteColor = Color(0xFFFFFFFF),
        backgroundApp = Color(0xFFFFFFFF),
        backgroundCard = Color(0xFFFB7D8A)
    ) else AppColor(
        blackColor = Color(0xFF000000),
        greenColor = Color(0xFF82B70B),
        whiteColor = Color(0xFFFFFFFF),
        backgroundApp = Color(0xFFFFFFFF),
        backgroundCard = Color(0xFFFB7D8A)
    )

    CompositionLocalProvider(LocalAppTypography provides typography, LocalAppColor provides color) {
        content.invoke()
    }
}

object MyAppTheme {
    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val color: AppColor
        @Composable get() = LocalAppColor.current
}