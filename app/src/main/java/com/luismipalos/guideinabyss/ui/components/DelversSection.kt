package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO

@Composable
fun DelversSection(
    delvers: List<DelverDTO>, onItemClick: (DelverDTO) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Lista de exploradores",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (delvers.isNotEmpty()) {
            val delversOficiales = delvers.filter {
                !it.original
            }
            val delversOriginales = delvers.filter {
                it.original
            }
            if (delversOficiales.isNotEmpty()) {
                Text(text = "Exploradores oficiales",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(delversOficiales.size) { index ->
                        val item = delversOficiales[index]
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .border(1.dp, Color.Gray)
                                .clickable { onItemClick(item) }
                        ) {
                            AsyncImage(
                                model = item.foto,
                                contentDescription = item.nombre,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            if (delversOriginales.isNotEmpty()) {
                Text(text = "Exploradores originales",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(delversOriginales.size) { index ->
                        val item = delversOriginales[index]
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .border(1.dp, Color.Gray)
                                .clickable { onItemClick(item) }
                        ) {
                            AsyncImage(
                                model = item.foto,
                                contentDescription = item.nombre,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        } else {
            Text(text = "No hay entradas de exploradores notables todavía")
        }
    }
}