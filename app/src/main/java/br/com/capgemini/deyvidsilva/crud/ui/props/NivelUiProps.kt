package br.com.capgemini.deyvidsilva.crud.ui.props

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.capgemini.deyvidsilva.crud.domain.entity.enums.Nivel

val Nivel.backgroundColor: Color
    get() = when (this) {
        Nivel.ADMINISTRATIVO -> Color(0xFFE3F2FD)
        Nivel.FINANCEIRO -> Color(0xFFE8F5E9)
        Nivel.GERENCIA -> Color(0xFFEDE7F6)
        Nivel.SUPORTE -> Color(0xFFFFF3E0)
    }

val Nivel.contentColor: Color
    get() = when (this) {
        Nivel.ADMINISTRATIVO -> Color(0xFF0D47A1)
        Nivel.FINANCEIRO -> Color(0xFF1B5E20)
        Nivel.GERENCIA -> Color(0xFF4A148C)
        Nivel.SUPORTE -> Color(0xFFE65100)
    }

val Nivel.icone: ImageVector
    get() = when (this) {
        Nivel.ADMINISTRATIVO -> Icons.Filled.BusinessCenter
        Nivel.FINANCEIRO -> Icons.Filled.AccountBalance
        Nivel.GERENCIA -> Icons.Filled.ManageAccounts
        Nivel.SUPORTE -> Icons.Filled.Engineering
    }