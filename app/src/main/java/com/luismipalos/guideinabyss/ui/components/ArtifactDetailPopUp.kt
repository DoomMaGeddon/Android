package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.luismipalos.guideinabyss.R
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO

@Composable
fun ArtifactDetailPopUp(artifact: ArtifactDTO, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (artifact.foto == "") {
                    Image(
                        painter = painterResource(id = R.drawable.unknown),
                        contentDescription = "Fauna",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp),
                    )
                } else {
                    AsyncImage(
                        model = artifact.foto,
                        contentDescription = artifact.nombre,
                        modifier = Modifier.fillMaxWidth().height(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(text = artifact.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Grado: ${artifact.grado}")
                Text(text = "Efecto: ${artifact.efecto}")
                Text(text = "Descripción: ${artifact.descripcion}")
                Text(text = "Origen: ${artifact.origen}")
                if (artifact.duenyoId != null) {
                    Text(text = "Dueño: Este artefacto es propiedad de un explorador")
                } else {
                    Text(text = "Dueño: Ninguno/Desconocido")
                }
                HorizontalDivider(modifier = Modifier.height(8.dp))
                CloseButton (onDismiss)
            }
        }
    }
}