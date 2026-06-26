package br.com.capgemini.deyvidsilva.crud.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel
import br.com.capgemini.deyvidsilva.crud.domain.usecase.CadastrarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.EditarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.RemoverColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.ui.state.FormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColaboradorViewModel : ViewModel() {

    private val listaInterna = mutableListOf<Colaborador>()

    private val _colaboradores = MutableStateFlow<List<Colaborador>>(emptyList())
    val colaboradores: StateFlow<List<Colaborador>> = _colaboradores

    private val _formState = MutableStateFlow(FormState())
    val formState: StateFlow<FormState> = _formState

    private val cadastrarUseCase = CadastrarColaboradorUseCase()
    private val editarUseCase = EditarColaboradorUseCase(listaInterna)
    private val removerUseCase = RemoverColaboradorUseCase(listaInterna)

    fun onNomeChange(nome: String) {
        _formState.value = _formState.value.copy(nome = nome)
    }

    fun onEmailChange(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun onNivelChange(nivel: Nivel) {
        _formState.value = _formState.value.copy(nivel = nivel)
    }

    fun salvar() {
        val state = _formState.value

        if (state.estaEditando) {
            val sucesso = editarUseCase(
                id = state.id!!,
                nome = state.nome,
                email = state.email,
                nivel = state.nivel
            )

            if (sucesso) {
                atualizarLista()
            }

        } else {
            val novo = cadastrarUseCase(
                nome = state.nome,
                email = state.email,
                nivel = state.nivel
            )

            listaInterna.add(novo)
            atualizarLista()
        }

        limparFormulario()
    }

    fun selecionarParaEditar(colaborador: Colaborador) {
        _formState.value = FormState(
            id = colaborador.id,
            nome = colaborador.nome,
            email = colaborador.email,
            nivel = colaborador.nivel,
            estaEditando = true
        )
    }

    fun cancelarEdicao() {
        limparFormulario()
    }


    private fun atualizarLista() {
        _colaboradores.value = listaInterna.toList()
    }

    private fun limparFormulario() {
        _formState.value = FormState()
    }
}