package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luismipalos.guideinabyss.ui.theme.GiASecondary

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(GiASecondary.value))
            .padding(bottom = 150.dp),
        contentAlignment = Alignment.Center
    ) {
        Logo()
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.BottomCenter)
            .size(60.dp))
    }
}