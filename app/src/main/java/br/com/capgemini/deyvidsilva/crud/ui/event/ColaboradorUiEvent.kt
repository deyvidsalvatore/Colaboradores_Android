package br.com.capgemini.deyvidsilva.crud.ui.event

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

sealed class ColaboradorUiEvent {

    data class OnNomeChange(val nome: String) : ColaboradorUiEvent()

    data class OnEmailChange(val email: String) : ColaboradorUiEvent()

    data class OnNivelChange(val nivel: Nivel) : ColaboradorUiEvent()

    object OnSalvar : ColaboradorUiEvent()

    object OnCancelar : ColaboradorUiEvent()

    data class OnSelecionar(val colaborador: Colaborador) : ColaboradorUiEvent()

    data class OnRemover(val id: Int) : ColaboradorUiEvent()

}