package br.com.capgemini.deyvidsilva.crud.ui.state

import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel


data class FormState(
    val id: Int? = null,
    val nome: String = "",
    val email: String = "",
    val nivel: Nivel = Nivel.SUPORTE,
    val estaEditando: Boolean = false
) {}
