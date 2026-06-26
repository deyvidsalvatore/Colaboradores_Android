package br.com.capgemini.deyvidsilva.crud.domain.usecase

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

class EditarColaboradorUseCase(
    private val colaboradores: MutableList<Colaborador>
) {
    operator fun invoke(
        id: Int, nome: String, email: String, nivel: Nivel
    ): Boolean {

        val index = colaboradores.indexOfFirst { it.id == id }
        if (index == -1) return false

        colaboradores[index] = colaboradores[index].copy(
            nome = nome, email = email, nivel = nivel
        )

        return true
    }
}