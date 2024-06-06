package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO

@Composable
fun FaunaDetailPopUp(animal: FaunaDTO, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = animal.foto,
                    contentDescription = animal.nombre,
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = animal.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Especie: ${animal.especie}")
                Text(text = "Dieta: ${animal.dieta}")
                Text(text = "Descripción: ${animal.descripcion}")
                HorizontalDivider(modifier = Modifier.height(8.dp))
                CloseButton (onDismiss)
            }
        }
    }
}
