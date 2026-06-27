package br.com.capgemini.deyvidsilva.crud.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
    erroNome: String? = null,
    erroEmail: String? = null,
    onNomeChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onNivelChange: (Nivel) -> Unit,
    onSalvar: () -> Unit,
    onCancelar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            AppTextField(
                value = nome,
                onValueChange = onNomeChange,
                label = "Nome",
                erro = erroNome,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                erro = erroEmail,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            var expanded by remember { mutableStateOf(false) }

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nivel.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Nível") },
                    trailingIcon = {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                            contentDescription = "Selecionar Nível"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor,
                        focusedLabelColor = OutlinedTextFieldDefaults.colors().unfocusedLabelColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Nivel.entries.forEach { itemNivel ->
                        DropdownMenuItem(
                            text = { Text(itemNivel.name) },
                            onClick = {
                                onNivelChange(itemNivel)
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
}