package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary

@Composable
fun SignUpButton(enabledSignup: Boolean, onSignupSelected: () -> Unit) {
    OutlinedButton(
        onClick = {onSignupSelected()},
        border = BorderStroke(1.dp, GiAPrimary),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = GiAPrimary,
            containerColor = Color.Black
        ),
        enabled = enabledSignup,
    ) {
        Text(text = "Registrarse")
    }
}