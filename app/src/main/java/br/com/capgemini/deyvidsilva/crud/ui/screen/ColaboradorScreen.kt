package br.com.capgemini.deyvidsilva.crud.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorCard
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorExclusionModal
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorForm
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEffect
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEvent
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColaboradorScreen(viewModel: ColaboradorViewModel) {

    val state = viewModel.state.collectAsState()
    var idParaRemover by remember { mutableStateOf<Int?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ColaboradorUiEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Gerenciamento Colaboradores") },
                navigationIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(ColaboradorUiEvent.OnSalvar)
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Salvar")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            ColaboradorForm(
                nome = state.value.nome,
                email = state.value.email,
                nivel = state.value.nivel,
                estaEditando = state.value.estaEditando,

                onNomeChange = {
                    viewModel.onEvent(ColaboradorUiEvent.OnNomeChange(it))
                },
                onEmailChange = {
                    viewModel.onEvent(ColaboradorUiEvent.OnEmailChange(it))
                },
                onNivelChange = {
                    viewModel.onEvent(ColaboradorUiEvent.OnNivelChange(it))
                },

                onSalvar = {
                    viewModel.onEvent(ColaboradorUiEvent.OnSalvar)
                },
                onCancelar = {
                    viewModel.onEvent(ColaboradorUiEvent.OnCancelar)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Colaboradores",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(
                    items = state.value.colaboradores,
                    key = { it.id }
                ) { colaborador ->

                    ColaboradorCard(
                        colaborador = colaborador,
                        onSelecionar = {
                            viewModel.onEvent(
                                ColaboradorUiEvent.OnSelecionar(colaborador)
                            )
                        },
                        onRemover = { id ->
                            idParaRemover = id
                        }
                    )
                }
            }
        }
    }

    ColaboradorExclusionModal(
        isOpen = idParaRemover != null,
        onConfirm = {
            idParaRemover?.let { id ->
                viewModel.onEvent(
                    ColaboradorUiEvent.OnRemover(id)
                )
            }
            idParaRemover = null
        },
        onDismiss = {
            idParaRemover = null
        }
    )
}