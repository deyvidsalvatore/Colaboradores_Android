package br.com.capgemini.deyvidsilva.crud.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

@Composable
fun ColaboradorForm(
    nome: String,
    email: String,
    nivel: Nivel,
    estaEditando: Boolean,
    onNomeChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onNivelChange: (Nivel) -> Unit,
    onSalvar: () -> Unit,
    onCancelar: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        AppTextField(
            value = nome,
            onValueChange = onNomeChange,
            label = "Nome",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }

        Box {
            AppButton(
                text = "Nível: $nivel",
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Nivel.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(it.name) },
                        onClick = {
                            onNivelChange(it)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = if (estaEditando) "Atualizar" else "Salvar",
            onClick = onSalvar,
            modifier = Modifier.fillMaxWidth()
        )

        if (estaEditando) {
            Spacer(modifier = Modifier.height(8.dp))

            AppButton(
                text = "Cancelar",
                onClick = onCancelar,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}