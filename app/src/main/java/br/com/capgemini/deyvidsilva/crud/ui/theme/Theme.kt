package br.com.capgemini.deyvidsilva.crud.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = AzureSecondary,
    background = BlueBackground,
    surface = SurfaceColor,
    onPrimary = SurfaceColor,
    onSecondary = SurfaceColor,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun Colaboradores_AndroidTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}