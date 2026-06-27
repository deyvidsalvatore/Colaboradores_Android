package br.com.capgemini.deyvidsilva.crud.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorCard
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorDetailsModal
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorExclusionModal
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorForm
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEffect
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEvent
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

@Composable
fun ColaboradorScreen(viewModel: ColaboradorViewModel) {

    val state = viewModel.state.collectAsState()
    var idParaRemover by remember { mutableStateOf<Int?>(null) }
    var colaboradorParaVisualizar by remember { mutableStateOf<Colaborador?>(null) }
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
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                tonalElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Gerenciamento Colaboradores",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
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
                erroNome = state.value.erroNome,
                erroEmail = state.value.erroEmail,
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

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Colaboradores Ativos",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(
                    items = state.value.colaboradores,
                    key = { it.id }
                ) { colaborador ->

                    ColaboradorCard(
                        colaborador = colaborador,
                        onVisualizar = {
                            colaboradorParaVisualizar = colaborador
                        },
                        onEditar = {
                            viewModel.onEvent(
                                ColaboradorUiEvent.OnSelecionar(colaborador)
                            )
                        },
                        onRemover = {
                            idParaRemover = colaborador.id
                        }
                    )
                }
            }
        }
    }

    ColaboradorDetailsModal(
        colaborador = colaboradorParaVisualizar,
        onDismiss = { colaboradorParaVisualizar = null }
    )

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