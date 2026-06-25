package br.com.capgemini.deyvidsilva.crud.domain.usecase

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador

class ListarColaboradoresUseCase(
    private val colaboradores: List<Colaborador>
) {
    operator fun invoke(): List<Colaborador> {
        return colaboradores;
    }
}