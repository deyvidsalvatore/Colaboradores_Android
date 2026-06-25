package br.com.capgemini.deyvidsilva.crud.domain.usecase

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador


class RemoverColaboradorUseCase(
    private val colaboradores: MutableList<Colaborador>
) {

    operator fun invoke(id: Int): Boolean {
        return colaboradores.removeIf { it.id == id }
    }
}
