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
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO

@Composable
fun DelversDetailPopUp(delver: DelverDTO, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                val rango: String = when (delver.rangoId) {
                    1 -> "Cascabel"
                    2 -> "Silbato rojo"
                    3 -> "Silbato azul"
                    4 -> "Silbato lunar"
                    5 -> "Silbato negro"
                    6 -> "Silbato blanco"
                    else -> "Sin rango"
                }
                
                if (delver.foto == "") {
                    Image(
                        painter = painterResource(id = R.drawable.unknown),
                        contentDescription = "Fauna",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp),
                    )
                } else {
                    AsyncImage(
                        model = delver.foto,
                        contentDescription = delver.nombre,
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(text = delver.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Género: ${delver.genero}")
                Text(text = "Especie: ${delver.especie}")
                Text(text = "Estado: ${delver.estado}")
                Text(text = "Rango: $rango")
                HorizontalDivider(modifier = Modifier.height(8.dp))
                CloseButton (onDismiss)
            }
        }
    }
}