package com.fiapos.weagle.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val NavyBlue = Color(0xFF162130)
var DarkGrey = Color(0xFF56667C)
var LighBlueyGrey = Color(0xFFD7E2F2)
val LightBlue = Color(0xFF3F6EA8)
val Background = Color(0xFFF6FAFF)
val White = Color(0xFFFFFFFF)

val AppColorScheme = lightColorScheme(
    primary = NavyBlue,
    secondary = DarkGrey,
    background = Background,
    surface = White,
    onPrimary = White,
    onBackground = NavyBlue,
    outline = LighBlueyGrey
)