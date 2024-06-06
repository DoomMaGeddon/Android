package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO

@Composable
fun FloraDetailPopUp(planta: FloraDTO, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = planta.foto,
                    contentDescription = planta.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = planta.nombre, style = MaterialTheme.typography.labelSmall)
                Text(text = "Especie: ${planta.especie}")
                Text(text = "Descripción: ${planta.descripcion}")
                HorizontalDivider(modifier = Modifier.height(8.dp))
                CloseButton (onDismiss)
            }
        }
    }
}
