package com.francielicampos.sohoje.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = lightColorScheme(
    primary = SageGreen,
    onPrimary = Color.White,
    primaryContainer = SageGreenLight,
    onPrimaryContainer = SageGreenDark,
    secondary = Terracotta,
    onSecondary = Color.White,
    secondaryContainer = TerracottaLight,
    tertiary = PetrolBlue,
    onTertiary = Color.White,
    tertiaryContainer = PetrolBlueLight,
    background = Sand,
    onBackground = InkText,
    surface = SurfaceWhite,
    onSurface = InkText,
    error = AlertSoft,
    onError = Color.White
)

@Composable
fun SoPorHojeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
