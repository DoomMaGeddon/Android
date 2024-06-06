package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary
import com.luismipalos.guideinabyss.ui.theme.GiATertiary

@Composable
fun LateralDrawer(navController: NavController) {
    Column {
        Text(
            text = "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        HorizontalDivider(color = GiATertiary)
        DrawerItem(text = "Capas", onItemClick = { navController.navigate("layers_screen") })
        DrawerItem(text = "Artefactos", onItemClick = { navController.navigate("artifacts_screen") })
        DrawerItem(text = "Exploradores", onItemClick = { navController.navigate("delvers_screen") })
        DrawerItem(text = "Perfil", onItemClick = { navController.navigate("profile_screen") })
    }
}

@Composable
fun DrawerItem(text: String, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = { onItemClick() })
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = GiAPrimary,
            )
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}
