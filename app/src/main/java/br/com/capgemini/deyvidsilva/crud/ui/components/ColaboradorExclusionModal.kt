package br.com.capgemini.deyvidsilva.crud.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ColaboradorExclusionModal(
    isOpen: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    if (!isOpen) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Confirmar exclusão")
        },
        text = {
            Text("Tem certeza que deseja excluir este colaborador?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Excluir")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
