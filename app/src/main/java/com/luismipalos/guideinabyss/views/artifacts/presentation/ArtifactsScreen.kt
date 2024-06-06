package com.luismipalos.guideinabyss.views.artifacts.presentation

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
import com.luismipalos.guideinabyss.ui.components.ArtifactDetailPopUp
import com.luismipalos.guideinabyss.ui.components.ArtifactSection
import com.luismipalos.guideinabyss.ui.components.CloseButton
import com.luismipalos.guideinabyss.ui.components.DataTextField
import com.luismipalos.guideinabyss.ui.components.LateralDrawer
import com.luismipalos.guideinabyss.ui.components.Loading
import com.luismipalos.guideinabyss.ui.components.SubmitButton
import com.luismipalos.guideinabyss.ui.components.TitleText
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary
import com.luismipalos.guideinabyss.ui.theme.GiASecondary
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtifactsScreen(viewModel: ArtifactsViewModel, navController: NavController) {
    val artifacts: List<ArtifactDTO>? by viewModel.artifacts.observeAsState(initial = null)
    val userRole: String? by viewModel.userRole.observeAsState(initial = null)
    val nombre: String by viewModel.nombre.observeAsState(initial = "")
    val foto: String by viewModel.foto.observeAsState(initial = "")
    val grado: String by viewModel.grado.observeAsState(initial = "")
    val efecto: String by viewModel.efecto.observeAsState(initial = "")
    val origen: String by viewModel.origen.observeAsState(initial = "")
    val descripcion: String by viewModel.descripcion.observeAsState(initial = "")
    val successfulArtifactPost: Boolean by viewModel.successfulArtifactPost.observeAsState(initial = false)
    val failedArtifactPost: Boolean by viewModel.failedArtifactPost.observeAsState(initial = false)
    val enabledSubmit: Boolean by viewModel.enabledSubmit.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showArtifacts by remember { mutableStateOf("") }
    var showArtifactFormulary by remember { mutableStateOf(false) }
    var selectedArtifact by remember { mutableStateOf<ArtifactDTO?>(null) }

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
                        painter = painterResource(id = R.drawable.school),
                        contentScale = ContentScale.Crop
                    )
                    .padding(padding),
            ) {
                if (artifacts == null) {
                    viewModel.loadArtifacts()
                }

                if (isLoading) {
                    Loading()
                } else {
                    Column (
                        modifier = Modifier
                            .background(GiASecondary.copy(alpha = 0.6f))
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Catálogo de artefactos",
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
                        ArtifactsButton ("Aubades") {
                            showArtifacts = "Aubade"
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        ArtifactsButton ("Grado special") {
                            showArtifacts = "Especial"
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        ArtifactsButton ("Primer grado") {
                            showArtifacts = "Primer"
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        ArtifactsButton ("Segundo grado") {
                            showArtifacts = "Segundo"
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        ArtifactsButton ("Tercer grado") {
                            showArtifacts = "Tercer"
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        ArtifactsButton ("Cuarto grado") {
                            showArtifacts = "Cuarto"
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        ArtifactsButton ("Desconocidos") {
                            showArtifacts = "Desconocido"
                        }
                        if (userRole == "Científico") {
                            Spacer(modifier = Modifier.width(10.dp))
                            ArtifactsButton("Crear artefacto") { showArtifactFormulary = true }
                        }
                    }

                    if (showArtifacts !== "") {
                        ArtifactSection(
                            artifacts = artifacts ?: emptyList(),
                            showArtifacts,
                            onItemClick = { selectedArtifact = it },
                            onDismiss = { showArtifacts = "" }
                        )
                    }

                    selectedArtifact?.let {
                        ArtifactDetailPopUp(artifact = it, onDismiss = { selectedArtifact = null })
                    }

                    if (showArtifactFormulary) {
                        Dialog(onDismissRequest = { viewModel.dismissDialog() }) {
                            Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TitleText(text = "Nuevo artefacto")

                                    DataTextField(text = "Nombre", icon = Icons.Default.TextFormat, data = nombre) {
                                        viewModel.onArtifactFormularyChange(it, grado, efecto, origen, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Foto (URL)", icon = Icons.Default.Face, data = foto)
                                    {}
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Grado", icon = Icons.Default.Grade, data = grado) {
                                        viewModel.onArtifactFormularyChange(nombre, it, efecto, origen, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Efecto", icon = Icons.Default.PlayCircleOutline, data = efecto) {
                                        viewModel.onArtifactFormularyChange(nombre, grado, it, origen, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Origen", icon = Icons.Default.TripOrigin, data = origen) {
                                        viewModel.onArtifactFormularyChange(nombre, grado, efecto, it, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Descripción", icon = Icons.Default.ChatBubble, data = descripcion) {
                                        viewModel.onArtifactFormularyChange(nombre, grado, efecto, origen, it)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row {
                                        CloseButton {
                                            viewModel.dismissDialog()
                                            showArtifactFormulary = false
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        SubmitButton ({ viewModel.onArtifactFormularySubmit() }, enabledSubmit)
                                    }
                                }
                            }
                        }
                    }

                    if (successfulArtifactPost) {
                        viewModel.loadArtifacts()
                        AlertDialog(
                            onDismissRequest = {},
                            title = {Text("Entrada creada correctamente")},
                            text = {Text("Se ha notificado a los demás usuarios para que disfruten de tu entrada.")},
                            confirmButton = {
                                Button(
                                    onClick = {coroutineScope.launch { viewModel.dismissDialog() }
                                        showArtifactFormulary = false
                                    }
                                ) {
                                    Text("Ok")
                                }
                            }
                        )
                    }

                    if (failedArtifactPost) {
                        AlertDialog(
                            onDismissRequest = {},
                            title = {Text("Error al crear la entrada")},
                            text = {Text("Ha habido un error. Inténtelo más tarde o notifique al administrador")},
                            confirmButton = {
                                Button(
                                    onClick = {coroutineScope.launch { viewModel.dismissDialog() }
                                        showArtifactFormulary = false
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

@Composable
fun ArtifactsButton(text: String, onClick: () -> Unit) {
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
