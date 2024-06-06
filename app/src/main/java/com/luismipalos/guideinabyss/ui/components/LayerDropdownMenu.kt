package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayerDropdown(selectedLayer: Int, onLayerSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val layers = (1..7).toList()

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Capa $selectedLayer",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(Color.White)
                .padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            layers.forEach { layer ->
                DropdownMenuItem(
                    text = {
                        Text(text = "Capa $layer")
                    },
                    onClick = {
                    onLayerSelected(layer)
                    expanded = false
                })
            }
        }
    }
}
