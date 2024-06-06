package com.luismipalos.guideinabyss.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO

@Composable
fun FloraSection(flora: List<FloraDTO>, onItemClick: (FloraDTO) -> Unit, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Flora", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                if (flora.isNotEmpty()) {
                    val floraOficial = flora.filter { !it.original }
                    val floraOriginal = flora.filter { it.original }
                    if (floraOficial.isNotEmpty()) {
                        Text(text = "Flora oficial",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp)
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            items(floraOficial.size) { index ->
                                val item = floraOficial[index]
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(1.dp, Color.Gray)
                                        .clickable { onItemClick(item) }
                                ) {
                                    if (item.foto == "") {
                                        Image(
                                            painter = painterResource(id = R.drawable.unknown),
                                            contentDescription = "Flora",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(vertical = 8.dp),
                                        )
                                    } else {
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
                    }

                    if (floraOriginal.isNotEmpty()) {
                        Text(text = "Flora oficial",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp)
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            items(floraOriginal.size) { index ->
                                val item = floraOriginal[index]
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(1.dp, Color.Gray)
                                        .clickable { onItemClick(item) }
                                ) {
                                    if (item.foto == "") {
                                        Image(
                                            painter = painterResource(id = R.drawable.unknown),
                                            contentDescription = "Flora",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(vertical = 8.dp),
                                        )
                                    } else {
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
                    }
                } else {
                    Text(text = "No hay entradas todavía")
                }
                HorizontalDivider(modifier = Modifier.height(8.dp))
                CloseButton (onDismiss)
            }
        }
    }



}
