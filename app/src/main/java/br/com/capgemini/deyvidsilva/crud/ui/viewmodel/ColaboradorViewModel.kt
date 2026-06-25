package br.com.capgemini.deyvidsilva.crud.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel
import br.com.capgemini.deyvidsilva.crud.domain.usecase.CadastrarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.EditarColaboradorUseCase
import br.com.capgemini.deyvidsilva.crud.domain.usecase.RemoverColaboradorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColaboradorViewModel : ViewModel() {

    private val listaInterna = mutableListOf<Colaborador>()

    private val _colaboradores = MutableStateFlow<List<Colaborador>>(emptyList())
    val colaboradores: StateFlow<List<Colaborador>> = _colaboradores

    private val cadastrarUseCase = CadastrarColaboradorUseCase()
    private val editarUseCase = EditarColaboradorUseCase(listaInterna)
    private val removerUseCase = RemoverColaboradorUseCase(listaInterna)

    fun cadastrar(nome: String, email: String, nivel: Nivel) {
        val novo = cadastrarUseCase(nome, email, nivel)
        listaInterna.add(novo)
        atualizarLista()
    }

    fun listar() {
        _colaboradores.value = listaInterna.toList()
    }

    fun editar(id: Int, nome: String, email: String, nivel: Nivel) {
        val sucesso = editarUseCase(id, nome, email, nivel)
        if (sucesso) atualizarLista()
    }

    fun remover(id: Int) {
        val sucesso = removerUseCase(id)
        if (sucesso) atualizarLista()
    }

    private fun atualizarLista() {
        _colaboradores.value = listaInterna.toList()
    }
}