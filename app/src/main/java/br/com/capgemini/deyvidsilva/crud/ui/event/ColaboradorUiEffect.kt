package br.com.capgemini.deyvidsilva.crud.ui.event

sealed class ColaboradorUiEffect {
    data class ShowSnackbar(val message: String) : ColaboradorUiEffect()
}