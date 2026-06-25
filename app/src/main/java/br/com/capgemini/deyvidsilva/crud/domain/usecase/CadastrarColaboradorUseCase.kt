package br.com.capgemini.deyvidsilva.crud.domain.usecase

import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

class CadastrarColaboradorUseCase {

    private val colaboradores = mutableListOf<Colaborador>();
    private var proximoId = 1;

    operator fun invoke(
        nome: String,
        email: String,
        nivel: Nivel
    ): Colaborador {
        val colaborador = Colaborador(
            id = proximoId++,
            nome = nome,
            email = email,
            nivel = nivel
        );

        colaboradores.add(colaborador);
        return colaborador;
    }

    fun getLista(): List<Colaborador> = colaboradores;

}