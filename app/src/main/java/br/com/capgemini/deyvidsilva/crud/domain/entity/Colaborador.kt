package br.com.capgemini.deyvidsilva.crud.domain.entity

import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

data class Colaborador(
    val id: Int,
    val nome: String,
    val email: String,
    val nivel: Nivel
) {

}