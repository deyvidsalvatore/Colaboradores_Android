package br.com.capgemini.deyvidsilva.crud.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEvent
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColaboradorScreen(viewModel: ColaboradorViewModel) {

    val state = viewModel.state.collectAsState()
    var idParaRemover by remember { mutableStateOf<Int?>(null) }

    Scaffold(
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
                        modifier = Modifier.safeDrawingPadding(),
                        onRemover = { id -> idParaRemover = id}
                    )
                }
            }
        }
    }

    ColaboradorExclusionModal(
        isOpen = idParaRemover != null,
        onConfirm = {
            viewModel.onEvent(
                ColaboradorUiEvent.OnRemover(idParaRemover!!)
            )
            idParaRemover = null
        },
        onDismiss = {
            idParaRemover = null
        }
    )
}