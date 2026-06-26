package br.com.capgemini.deyvidsilva.crud.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.capgemini.deyvidsilva.crud.domain.entity.Colaborador

@Composable
fun ColaboradorCard(
    colaborador: Colaborador,
    onSelecionar: (Colaborador) -> Unit,
    onRemover: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("Nome: ${colaborador.nome}")
            Text("Email: ${colaborador.email}")
            Text("Nível: ${colaborador.nivel}")

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                AppButton(
                    text = "Selecionar",
                    onClick = { onSelecionar(colaborador) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                AppButton(
                    text = "Apagar",
                    onClick = { onRemover(colaborador.id) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}