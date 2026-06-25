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
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorForm
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColaboradorScreen(viewModel: ColaboradorViewModel) {

    val formState = viewModel.formState.collectAsState()
    val lista = viewModel.colaboradores.collectAsState()

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
                onClick = { viewModel.salvar() }
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
                nome = formState.value.nome,
                email = formState.value.email,
                nivel = formState.value.nivel,

                onNomeChange = viewModel::onNomeChange,
                onEmailChange = viewModel::onEmailChange,
                onNivelChange = viewModel::onNivelChange,

                onSalvar = { viewModel.salvar() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Colaboradores",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(lista.value) { colaborador ->
                    Text("Nome: ${colaborador.nome}")
                    Text("E-mail: ${colaborador.email}")
                    Text("Nível: ${colaborador.nivel}")
                }
            }
        }
    }
}