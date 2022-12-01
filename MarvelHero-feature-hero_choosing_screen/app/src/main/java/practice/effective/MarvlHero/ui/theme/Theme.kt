package practice.effective.MarvlHero.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//to do: change architecture
private val DarkColorPalette = darkColors(
    primary = DarkGray,
    primaryVariant = Purple700,
    secondary = LighterGray,
    background = Color.Black,

)

private val LightColorPalette = lightColors(
    primary = DarkGray,
    primaryVariant = Purple700,
    secondary = LighterGray,
    background = Color.Black,
)

@Composable
fun ChooseYourHeroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
