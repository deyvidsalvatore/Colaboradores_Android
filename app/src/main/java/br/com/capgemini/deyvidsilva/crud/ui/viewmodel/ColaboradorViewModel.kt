package br.com.capgemini.deyvidsilva.crud.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.usecase.CadastrarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.EditarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.RemoverColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.ValidarFormularioUseCase
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEffect
import br.com.capgemini.deyvidsilva.crud.ui.event.ColaboradorUiEvent
import br.com.capgemini.deyvidsilva.crud.ui.state.ColaboradorViewState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColaboradorViewModel : ViewModel() {


    private val _effect = MutableSharedFlow<ColaboradorUiEffect>()
    val effect: SharedFlow<ColaboradorUiEffect> = _effect

    private val listaInterna = mutableListOf<Colaborador>()

    private val cadastrarUseCase = CadastrarColaboradorUseCase()
    private val editarUseCase = EditarColaboradorUseCase(listaInterna)
    private val removerUseCase = RemoverColaboradorUseCase(listaInterna)

    private val validarFormularioUseCase = ValidarFormularioUseCase()

    private val _state = MutableStateFlow(ColaboradorViewState())
    val state: StateFlow<ColaboradorViewState> = _state

    fun onEvent(event: ColaboradorUiEvent) {
        when (event) {

            is ColaboradorUiEvent.OnNomeChange -> {
                _state.value = _state.value.copy(
                    nome = event.nome,
                    erroNome = null
                )
            }

            is ColaboradorUiEvent.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    erroEmail = null
                )
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
                    estaEditando = true,
                    erroNome = null,
                    erroEmail = null
                )
            }

            ColaboradorUiEvent.OnSalvar -> {
                val s = _state.value

                val (erroNome, erroEmail) = validarFormularioUseCase(s.nome, s.email)

                if (erroNome != null || erroEmail != null) {
                    _state.value = _state.value.copy(
                        erroNome = erroNome,
                        erroEmail = erroEmail
                    )
                    return
                }

                if (s.estaEditando) {
                    editarUseCase(
                        id = listaInterna.find { it.nome == s.nome }?.id ?: return,
                        nome = s.nome,
                        email = s.email,
                        nivel = s.nivel
                    )
                    enviarSnackbar("Atualizado com sucesso ✅")
                } else {
                    val novo = cadastrarUseCase(
                        nome = s.nome,
                        email = s.email,
                        nivel = s.nivel
                    )
                    listaInterna.add(novo)
                    enviarSnackbar("Salvo com sucesso ✅")
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
                enviarSnackbar("Excluído com sucesso 🗑️")
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
            estaEditando = false,
            erroNome = null,
            erroEmail = null
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun enviarSnackbar(msg: String) {
        viewModelScope.launch {
            _effect.emit(ColaboradorUiEffect.ShowSnackbar(msg))
        }
    }
}