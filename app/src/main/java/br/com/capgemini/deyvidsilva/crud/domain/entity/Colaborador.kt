package br.com.capgemini.deyvidsilva.crud.domain.entity

import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

data class Colaborador(
    val id: Int,
    var nome: String,
    var email: String,
    var nivel: Nivel
) {

}