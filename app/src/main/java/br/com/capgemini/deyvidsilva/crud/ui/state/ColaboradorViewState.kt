package br.com.capgemini.deyvidsilva.crud.ui.state

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

data class ColaboradorViewState(
    val nome: String = "",
    val email: String = "",
    val nivel: Nivel = Nivel.ADMINISTRATIVO,
    val estaEditando: Boolean = false,
    val colaboradores: List<Colaborador> = emptyList()
)
