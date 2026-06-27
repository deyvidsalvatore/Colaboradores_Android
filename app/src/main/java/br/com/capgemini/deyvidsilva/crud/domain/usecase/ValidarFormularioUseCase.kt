package br.com.capgemini.deyvidsilva.crud.domain.usecase

class ValidarFormularioUseCase {

    operator fun invoke(nome: String, email: String): Pair<String?, String?> {
        return validarNome(nome) to validarEmail(email)
    }

    private fun validarNome(nome: String): String? {
        val regexNumeros = ".*\\d+.*".toRegex()

        return when {
            nome.isBlank() -> "O nome não pode ser vazio"
            nome.length !in 3..50 -> "O nome deve ter entre 3 e 50 caracteres"
            regexNumeros.matches(nome) -> "O nome não pode conter números"
            else -> null
        }
    }

    private fun validarEmail(email: String): String? {
        val emailPattern =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

        return when {
            email.isBlank() -> "O e-mail não pode ser vazio"
            !email.matches(emailPattern) -> "Insira um e-mail válido (exemplo@email.com)"
            else -> null
        }
    }
}