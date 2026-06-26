package br.com.capgemini.deyvidsilva.crud.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.usecase.CadastrarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.EditarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.RemoverColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEvent
import br.com.capgemini.deyvidsilva.crud.ui.state.ColaboradorViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColaboradorViewModel : ViewModel() {

    private val listaInterna = mutableListOf<Colaborador>()

    private val cadastrarUseCase = CadastrarColaboradorUseCase()
    private val editarUseCase = EditarColaboradorUseCase(listaInterna)
    private val removerUseCase = RemoverColaboradorUseCase(listaInterna)

    private val _state = MutableStateFlow(ColaboradorViewState())
    val state: StateFlow<ColaboradorViewState> = _state

    fun onEvent(event: ColaboradorUiEvent) {
        when (event) {

            is ColaboradorUiEvent.OnNomeChange -> {
                _state.value = _state.value.copy(nome = event.nome)
            }

            is ColaboradorUiEvent.OnEmailChange -> {
                _state.value = _state.value.copy(email = event.email)
            }

            is ColaboradorUiEvent.OnNivelChange -> {
                _state.value = _state.value.copy(nivel = event.nivel)
            }

            is ColaboradorUiEvent.OnSelecionar -> {
                val c = event.colaborador
                _state.value = _state.value.copy(
                    nome = c.nome,
                    email = c.email,
                    nivel = c.nivel,
                    estaEditando = true
                )
            }

            ColaboradorUiEvent.OnSalvar -> {
                val s = _state.value

                if (s.estaEditando) {
                    editarUseCase(
                        id = listaInterna.find { it.nome == s.nome }?.id ?: return,
                        nome = s.nome,
                        email = s.email,
                        nivel = s.nivel
                    )
                } else {
                    val novo = cadastrarUseCase(
                        nome = s.nome,
                        email = s.email,
                        nivel = s.nivel
                    )
                    listaInterna.add(novo)
                }

                atualizarLista()
                limparFormulario()
            }

            ColaboradorUiEvent.OnCancelar -> {
                limparFormulario()
            }

            is ColaboradorUiEvent.OnRemover -> {
                removerUseCase(event.id)
                atualizarLista()
            }
        }
    }

    private fun atualizarLista() {
        _state.value = _state.value.copy(
            colaboradores = listaInterna.toList()
        )
    }

    private fun limparFormulario() {
        _state.value = _state.value.copy(
            nome = "",
            email = "",
            estaEditando = false
        )
    }
}