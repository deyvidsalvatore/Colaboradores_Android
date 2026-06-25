package br.com.capgemini.deyvidsilva.crud.ui.state

import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel


data class FormState(
    val nome: String = "",
    val email: String = "",
    val nivel: Nivel = Nivel.SUPORTE
) {}
