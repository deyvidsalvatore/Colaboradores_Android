package br.com.capgemini.deyvidsilva.crud.ui.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import br.com.capgemini.deyvidsilva.crud.ui.components.ColaboradorForm
import br.com.capgemini.deyvidsilva.crud.ui.viewmodel.ColaboradorViewModel

@Composable
fun FormContainer(viewModel: ColaboradorViewModel) {

    val formState = viewModel.formState.collectAsState()

    ColaboradorForm(
        nome = formState.value.nome,
        email = formState.value.email,
        nivel = formState.value.nivel,

        onNomeChange = viewModel::onNomeChange,
        onEmailChange = viewModel::onEmailChange,
        onNivelChange = viewModel::onNivelChange,

        onSalvar = { viewModel.salvar() }
    )
}