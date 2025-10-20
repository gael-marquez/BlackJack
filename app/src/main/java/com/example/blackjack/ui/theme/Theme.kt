package com.example.blackjack.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Esquema de colores oscuro para el tema de BlackJack
 */
private val DarkColorScheme = darkColorScheme(
    primary = GoldYellow,
    secondary = Silver,
    tertiary = LightGreen,
    background = DarkGreen,
    surface = DarkGreen,
    onPrimary = CardBlack,
    onSecondary = CardBlack,
    onTertiary = CardWhite,
    onBackground = CardWhite,
    onSurface = CardWhite,
)

/**
 * Esquema de colores claro para el tema de BlackJack
 */
private val LightColorScheme = lightColorScheme(
    primary = DarkGreen,
    secondary = LightGreen,
    tertiary = GoldYellow,
    background = LightBackground,
    surface = CardWhite,
    onPrimary = CardWhite,
    onSecondary = CardWhite,
    onTertiary = CardBlack,
    onBackground = CardBlack,
    onSurface = CardBlack,
)

/**
 * Tema principal de la aplicación BlackJack
 * @param darkTheme Si debe usar el tema oscuro
 * @param content El contenido de la aplicación
 */
@Composable
fun BlackJackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
