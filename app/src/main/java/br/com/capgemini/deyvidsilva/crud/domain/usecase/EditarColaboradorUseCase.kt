package br.com.capgemini.deyvidsilva.crud.domain.usecase

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

class EditarColaboradorUseCase(
    private val colaboradores: MutableList<Colaborador>
) {
    operator fun invoke(
        id: Int,
        nome: String,
        email: String,
        nivel: Nivel
    ): Boolean {
        val colaborador = colaboradores.find { it.id == id } ?: return false;
        colaborador.nome = nome;
        colaborador.email = email;
        colaborador .nivel = nivel;
        return true;
    }
}