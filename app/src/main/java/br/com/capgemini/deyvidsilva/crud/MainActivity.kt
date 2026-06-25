package br.com.capgemini.deyvidsilva.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.capgemini.deyvidsilva.crud.ui.screen.ColaboradorScreen
import br.com.capgemini.deyvidsilva.crud.ui.theme.GerenciamentoColaboradoresTheme
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = ColaboradorViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GerenciamentoColaboradoresTheme {
                ColaboradorScreen(viewModel)
            }
        }
    }
}