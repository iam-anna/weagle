package com.fiapos.weagle.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val NavyBlue = Color(0xFF162130)
var DarkGrey60 = Color(0xFF64748B)
var DarkGrey80 = Color(0xFF56667C)
var LighBlueyGrey = Color(0xFFD7E2F2)

object CombinatedColor {
    val lightBlue = Color(0xFF87BAF3)
    val lightBlueForeground = Color(0xFF001F3E)
}

val Background = Color(0xFFF6FAFF)
val White = Color(0xFFFFFFFF)

val AppColorScheme = lightColorScheme(
    primary = NavyBlue,
    secondary = DarkGrey80,
    tertiary = DarkGrey60,
    background = Background,
    surface = White,
    onPrimary = White,
    onBackground = NavyBlue,
    outline = LighBlueyGrey
)