package com.luismipalos.guideinabyss.views.delvers.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.R
import com.luismipalos.guideinabyss.ui.components.CloseButton
import com.luismipalos.guideinabyss.ui.components.DataTextField
import com.luismipalos.guideinabyss.ui.components.DelversDetailPopUp
import com.luismipalos.guideinabyss.ui.components.DelversSection
import com.luismipalos.guideinabyss.ui.components.LateralDrawer
import com.luismipalos.guideinabyss.ui.components.Loading
import com.luismipalos.guideinabyss.ui.components.SubmitButton
import com.luismipalos.guideinabyss.ui.components.TitleText
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary
import com.luismipalos.guideinabyss.ui.theme.GiASecondary
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DelversScreen(viewModel: DelversViewModel, navController: NavController) {
    val delvers: List<DelverDTO>? by viewModel.delvers.observeAsState(initial = null)
    val userRole: String? by viewModel.userRole.observeAsState(initial = null)
    val nombre: String by viewModel.nombre.observeAsState(initial = "")
    val foto: String by viewModel.foto.observeAsState(initial = "")
    val genero: String by viewModel.genero.observeAsState(initial = "")
    val especie: String by viewModel.especie.observeAsState(initial = "")
    val estado: String by viewModel.estado.observeAsState(initial = "")
    val rango: String by viewModel.rango.observeAsState(initial = "")
    val successfulDelverPost: Boolean by viewModel.successfulDelverPost.observeAsState(initial = false)
    val failedDelverPost: Boolean by viewModel.failedDelverPost.observeAsState(initial = false)
    val enabledSubmit: Boolean by viewModel.enabledSubmit.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedDelver by remember { mutableStateOf<DelverDTO?>(null) }
    var showDelverFormulary by remember { mutableStateOf(false) }

    if (delvers == null) {
         viewModel.loadDelvers()
    }

    ModalNavigationDrawer(
        drawerContent = {
            LateralDrawer(navController = navController)
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("Guide in Abyss") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.delverguild),
                        contentScale = ContentScale.Crop
                    )
                    .padding(padding),
            ) {
                if (delvers == null) {
                    viewModel.loadDelvers()
                }

                if (isLoading) {
                    Loading()
                } else {
                    Column (
                        modifier = Modifier
                            .background(GiASecondary.copy(alpha = 0.6f))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Exploradores notables",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        DelversSection(
                            delvers ?: emptyList(),
                            onItemClick = { selectedDelver = it }
                        )
                        if (userRole == "Científico") {
                            Spacer(modifier = Modifier.width(10.dp))
                            DelversButton("Crear explorador/a") { showDelverFormulary = true }
                        }

                        selectedDelver?.let {
                            DelversDetailPopUp(delver = it, onDismiss = { selectedDelver = null })
                        }

                        if (showDelverFormulary) {
                            Dialog(onDismissRequest = { viewModel.dismissDialog() }) {
                                Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        TitleText(text = "Nuevo/a explorador/a")

                                        DataTextField(text = "Nombre", icon = Icons.Default.TextFormat, data = nombre) {
                                            viewModel.onDelverFormularyChange(it, genero, especie, estado, rango)
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                        DataTextField(text = "Foto (URL)", icon = Icons.Default.Face, data = foto)
                                        {}
                                        Spacer(modifier = Modifier.height(5.dp))
                                        DataTextField(text = "Genero", icon = Icons.Default.Grade, data = genero) {
                                            viewModel.onDelverFormularyChange(nombre, it, especie, estado, rango)
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                        DataTextField(text = "Especie", icon = Icons.Default.PlayCircleOutline, data = especie) {
                                            viewModel.onDelverFormularyChange(nombre, genero, it, estado, rango)
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                        DataTextField(text = "Estado", icon = Icons.Default.TripOrigin, data = estado) {
                                            viewModel.onDelverFormularyChange(nombre, genero, especie, it, rango)
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                        DataTextField(text = "Rango", icon = Icons.Default.ChatBubble, data = rango) {
                                            viewModel.onDelverFormularyChange(nombre, genero, especie, estado, it)
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))

                                        Row {
                                            CloseButton {
                                                viewModel.dismissDialog()
                                                showDelverFormulary = false
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            SubmitButton ({ viewModel.onDelverFormularySubmit() }, enabledSubmit)
                                        }
                                    }
                                }
                            }
                        }

                        if (successfulDelverPost) {
                            viewModel.loadDelvers()
                            AlertDialog(
                                onDismissRequest = {},
                                title = {Text("Entrada creada correctamente")},
                                text = {Text("Se ha notificado a los demás usuarios para que disfruten de tu entrada.")},
                                confirmButton = {
                                    Button(
                                        onClick = {coroutineScope.launch { viewModel.dismissDialog() }
                                            showDelverFormulary = false
                                        }
                                    ) {
                                        Text("Ok")
                                    }
                                }
                            )
                        }

                        if (failedDelverPost) {
                            AlertDialog(
                                onDismissRequest = {},
                                title = {Text("Error al crear la entrada")},
                                text = {Text("Ha habido un error. Inténtelo más tarde o notifique al administrador")},
                                confirmButton = {
                                    Button(
                                        onClick = {coroutineScope.launch { viewModel.dismissDialog() }
                                            showDelverFormulary = false
                                        }
                                    ) {
                                        Text("Ok")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DelversButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        border = BorderStroke(1.dp, GiAPrimary),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = GiAPrimary,
            containerColor = Color.Black
        )
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}
