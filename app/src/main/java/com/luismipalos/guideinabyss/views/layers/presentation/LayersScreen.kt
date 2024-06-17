package com.luismipalos.guideinabyss.views.layers.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.R
import com.luismipalos.guideinabyss.ui.components.CloseButton
import com.luismipalos.guideinabyss.ui.components.DataTextField
import com.luismipalos.guideinabyss.ui.components.FaunaDetailPopUp
import com.luismipalos.guideinabyss.ui.components.FaunaSection
import com.luismipalos.guideinabyss.ui.components.FloraDetailPopUp
import com.luismipalos.guideinabyss.ui.components.FloraSection
import com.luismipalos.guideinabyss.ui.components.LateralDrawer
import com.luismipalos.guideinabyss.ui.components.LayerDropdown
import com.luismipalos.guideinabyss.ui.components.LayerInfo
import com.luismipalos.guideinabyss.ui.components.Loading
import com.luismipalos.guideinabyss.ui.components.SubmitButton
import com.luismipalos.guideinabyss.ui.components.TitleText
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary
import com.luismipalos.guideinabyss.ui.theme.GiASecondary
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayersScreen(viewModel: LayersViewModel, navController: NavController) {
    val fauna: List<FaunaDTO>? by viewModel.fauna.observeAsState(initial = null)
    val flora: List<FloraDTO>? by viewModel.flora.observeAsState(initial = null)
    val userRank: Int? by viewModel.userRank.observeAsState(initial = null)
    val userRole: String? by viewModel.userRole.observeAsState(initial = null)
    val nombre: String by viewModel.nombre.observeAsState(initial = "")
    val foto: String by viewModel.foto.observeAsState(initial = "")
    val especie: String by viewModel.especie.observeAsState(initial = "")
    val peligro: String by viewModel.peligro.observeAsState(initial = "")
    val dieta: String by viewModel.dieta.observeAsState(initial = "")
    val descripcion: String by viewModel.descripcion.observeAsState(initial = "")
    val enabledSubmit: Boolean by viewModel.enabledSubmit.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val successfulAnimalPost: Boolean by viewModel.successfulAnimalPost.observeAsState(initial = false)
    val successfulPlantPost: Boolean by viewModel.successfulPlantPost.observeAsState(initial = false)
    val failedAnimalPost: Boolean by viewModel.failedAnimalPost.observeAsState(initial = false)
    val failedPlantPost: Boolean by viewModel.failedPlantPost.observeAsState(initial = false)
    var selectedLayer by remember { mutableIntStateOf(1) }
    var selectedFauna by remember { mutableStateOf<FaunaDTO?>(null) }
    var selectedFlora by remember { mutableStateOf<FloraDTO?>(null) }
    var showFauna by remember { mutableStateOf(false) }
    var showFlora by remember { mutableStateOf(false) }
    var showFaunaFormulary by remember { mutableStateOf(false) }
    var showFloraFormulary by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

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
                        painter = painterResource(id = R.drawable.backgroundlayers),
                        contentScale = ContentScale.Crop
                    )
                    .padding(padding),
            ) {
                if (fauna == null && flora == null) {
                    viewModel.onLayerSelected(1)
                }

                if (isLoading) {
                    Loading()
                } else {
                    Column (
                        modifier = Modifier
                            .background(GiASecondary.copy(alpha = 0.6f))
                            .padding(10.dp)
                    ) {
                        LayerDropdown(selectedLayer = selectedLayer, onLayerSelected = {
                            selectedLayer = it
                            viewModel.onLayerSelected(it)
                        })
                        LayerInfo(numCapa = selectedLayer)

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            LayerButtons("Fauna") { showFauna = true }
                            Spacer(modifier = Modifier.width(50.dp))
                            LayerButtons("Flora") { showFlora = true }
                        }

                        if (userRole == "Científico" && (userRank ?: 1) >= selectedLayer) {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                LayerButtons("Crear fauna") { showFaunaFormulary = true }
                                Spacer(modifier = Modifier.width(10.dp))
                                LayerButtons("Crear flora") { showFloraFormulary = true }
                            }
                        }
                    }

                    if (showFauna) {
                        FaunaSection(
                            fauna = fauna ?: emptyList(),
                            onItemClick = { selectedFauna = it },
                            onDismiss = { showFauna = false }
                        )
                    }

                    selectedFauna?.let {
                        FaunaDetailPopUp(animal = it, onDismiss = { selectedFauna = null })
                    }

                    if (showFlora) {
                        FloraSection(
                            flora = flora ?: emptyList(),
                            onItemClick = { selectedFlora = it },
                            onDismiss = { showFlora = false }
                        )
                    }

                    selectedFlora?.let {
                        FloraDetailPopUp(planta = it, onDismiss = { selectedFlora = null })
                    }

                    if (showFaunaFormulary) {
                        Dialog(onDismissRequest = { viewModel.dismissDialog() }) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TitleText(text = "Nuevo animal")

                                    DataTextField(text = "Nombre", icon = Icons.Default.TextFormat, data = nombre) {
                                        viewModel.onFaunaFormularyChange(it, especie, peligro, dieta, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Foto (URL)", icon = Icons.Default.Face, data = foto)
                                    {}
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Especie", icon = Icons.Default.Pets, data = especie) {
                                        viewModel.onFaunaFormularyChange(nombre, it, peligro, dieta, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Peligro", icon = Icons.Default.Warning, data = peligro) {
                                        viewModel.onFaunaFormularyChange(nombre, especie, it, dieta, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Dieta", icon = Icons.Default.Fastfood, data = dieta) {
                                        viewModel.onFaunaFormularyChange(nombre, especie, peligro, it, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Descripción", icon = Icons.Default.ChatBubble, data = descripcion) {
                                        viewModel.onFaunaFormularyChange(nombre, especie, peligro, dieta, it)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row {
                                        CloseButton { viewModel.dismissDialog() }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        SubmitButton ({ viewModel.onFaunaFormularySubmit(selectedLayer) }, enabledSubmit)
                                    }
                                }
                            }
                        }
                    }

                    if (showFloraFormulary) {
                        Dialog(onDismissRequest = { viewModel.dismissDialog() }) {
                            Surface(shape = RoundedCornerShape(8.dp), color = Color.White, modifier = Modifier.padding(16.dp)) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TitleText(text = "Nueva planta")

                                    DataTextField(text = "Nombre", icon = Icons.Default.TextFormat, data = nombre) {
                                        viewModel.onFloraFormularyChange(it, especie, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Foto (URL)", icon = Icons.Default.Face, data = foto)
                                    {}
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Especie", icon = Icons.Default.Pets, data = especie) {
                                        viewModel.onFloraFormularyChange(nombre, it, descripcion)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    DataTextField(text = "Descripción", icon = Icons.Default.ChatBubble, data = descripcion) {
                                        viewModel.onFloraFormularyChange(nombre, especie, it)
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row {
                                        CloseButton {
                                            viewModel.dismissDialog()
                                            showFloraFormulary = false
                                            showFaunaFormulary = false
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        SubmitButton ({ viewModel.onFloraFormularySubmit(selectedLayer) }, enabledSubmit)
                                    }
                                }
                            }
                        }
                    }

                    if (successfulAnimalPost || successfulPlantPost) {
                        viewModel.onLayerSelected(selectedLayer)
                        AlertDialog(
                            onDismissRequest = {},
                            title = {Text("Entrada creada correctamente")},
                            text = {Text("Se ha notificado a los demás usuarios para que disfruten de tu entrada.")},
                            confirmButton = {
                                Button(
                                    onClick = {coroutineScope.launch {
                                        viewModel.dismissDialog()}
                                        showFaunaFormulary = false
                                        showFloraFormulary = false
                                    }
                                ) {
                                    Text("Ok")
                                }
                            }
                        )
                    }

                    if (failedAnimalPost || failedPlantPost) {
                        AlertDialog(
                            onDismissRequest = {},
                            title = {Text("Error al crear la entrada")},
                            text = {Text("Ha habido un error. Inténtelo más tarde o notifique al administrador")},
                            confirmButton = {
                                Button(
                                    onClick = {coroutineScope.launch {
                                        viewModel.dismissDialog()}
                                        showFaunaFormulary = false
                                        showFloraFormulary = false
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
fun LayerButtons(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, GiAPrimary),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = GiAPrimary,
            containerColor = Color.Black
        )
    ) {
        Text(text = text)
    }
}

