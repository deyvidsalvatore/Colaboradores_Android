package br.com.capgemini.deyvidsilva.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.com.capgemini.deyvidsilva.crud.ui.screen.ColaboradorScreen
import br.com.capgemini.deyvidsilva.crud.ui.screen.SplashScreen
import br.com.capgemini.deyvidsilva.crud.ui.theme.Colaboradores_AndroidTheme
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = ColaboradorViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Colaboradores_AndroidTheme {
                var mostrarSplash by remember { mutableStateOf(true) }

                if (mostrarSplash) {
                    SplashScreen(
                        onTimeout = {
                            mostrarSplash = false
                        }
                    )
                } else {
                    ColaboradorScreen(viewModel)
                }
            }
        }
    }
}