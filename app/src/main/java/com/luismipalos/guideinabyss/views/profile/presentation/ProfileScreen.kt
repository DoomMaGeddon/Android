package com.luismipalos.guideinabyss.views.profile.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.luismipalos.guideinabyss.R
import com.luismipalos.guideinabyss.ui.components.CloseButton
import com.luismipalos.guideinabyss.ui.components.DataTextField
import com.luismipalos.guideinabyss.ui.components.LateralDrawer
import com.luismipalos.guideinabyss.ui.components.PasswordTextField
import com.luismipalos.guideinabyss.ui.components.SubmitButton
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary
import com.luismipalos.guideinabyss.ui.theme.GiASecondary
import com.luismipalos.guideinabyss.views.profile.data.network.dto.EntryHistoryDTO
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val nombreUsuario: String by viewModel.nombreUsuario.observeAsState(initial = "")
    val fotoPerfil: String? by viewModel.fotoPerfil.observeAsState(initial = null)
    val contrasenya: String by viewModel.contrasenya.observeAsState(initial = "")
    val descripcion: String? by viewModel.descripcion.observeAsState(initial = "")
    val experiencia: Int by viewModel.experiencia.observeAsState(initial = 0)
    val rango: Int? by viewModel.rango.observeAsState(initial = null)
    val notificaciones: Boolean by viewModel.notificaciones.observeAsState(initial = false)
    val rol: String? by viewModel.rol.observeAsState(initial = "")
    val descripcionFormulario: String? by viewModel.descripcionFormulario.observeAsState(initial = "")
    val historialEntradas: EntryHistoryDTO by viewModel.historialEntradas.observeAsState(initial = EntryHistoryDTO())
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val enabledUpdate: Boolean by viewModel.enableUpdate.observeAsState(initial = false)
    val successfulUpdate: Boolean by viewModel.successfulUpdate.observeAsState(initial = false)
    val successfulFormularySubmit: Boolean by viewModel.successfulFormularySubmit.observeAsState(initial = false)
    val failedUpdate: Boolean by viewModel.failedUpdate.observeAsState(initial = false)
    val failedLogOut: Boolean by viewModel.failedLogOut.observeAsState(initial = false)
    var showRoleFormulary by remember { mutableStateOf(false) }
    var showEntryHistory by remember { mutableStateOf(false) }

    if (email == "") {
        coroutineScope.launch { viewModel.loadUserData() }
    }

    val rangoText: String = when (rango) {
        1 -> "Cascabel"
        2 -> "Silbato rojo"
        3 -> "Silbato azul"
        4 -> "Silbato lunar"
        5 -> "Silbato negro"
        6 -> "Silbato blanco"
        else -> "Sin rango"
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
                        painter = painterResource(id = R.drawable.backgroundranks),
                        contentScale = ContentScale.Crop
                    )
                    .padding(padding),
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        if (fotoPerfil !== "" && fotoPerfil !== null) {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Foto de perfil",
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(150.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, GiAPrimary, CircleShape)
                            )
                        } else {
                            AsyncImage(
                                model = fotoPerfil,
                                contentDescription = "Foto de perfil",
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(150.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, GiAPrimary, CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .border(
                                        width = 2.dp,
                                        color = GiAPrimary,
                                    )
                                    .background(Color.White)
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = "Experiencia: $experiencia",
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .border(
                                        width = 2.dp,
                                        color = GiAPrimary,
                                    )
                                    .background(Color.White)
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = "Rango: $rangoText",
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                    DataTextField("Nombre de usuario", Icons.Default.Face, nombreUsuario, false)
                        {
                            viewModel.onUpdateChanged(
                                it,
                                fotoPerfil ?: "",
                                contrasenya,
                                descripcion ?: "",
                                notificaciones)
                        }
                    Spacer(modifier = Modifier.height(5.dp))
                    DataTextField("Correo electrónico", Icons.Default.Mail, email, false)
                        {}
                    Spacer(modifier = Modifier.height(5.dp))
                    PasswordTextField(contrasenya)
                        {
                            viewModel.onUpdateChanged(
                                nombreUsuario,
                                fotoPerfil ?: "",
                                it,
                                descripcion ?: "",
                                notificaciones)
                        }
                    Spacer(modifier = Modifier.height(5.dp))
                    DataTextField("Descripción", Icons.Default.Info, descripcion)
                        {
                            viewModel.onUpdateChanged(
                                nombreUsuario,
                                fotoPerfil ?: "",
                                contrasenya,
                                it,
                                notificaciones)
                        }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .border(
                                    width = 2.dp,
                                    color = GiAPrimary,
                                )
                                .background(Color.White)
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "Notificaciones",
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                        Switch(
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = Color(GiAPrimary.value),
                                uncheckedTrackColor = Color(GiASecondary.value)
                            ),
                            checked = notificaciones,
                            onCheckedChange = {
                                viewModel.onNotificationsChanged(it)
                                viewModel.onUpdateChanged(
                                    nombreUsuario,
                                    fotoPerfil ?: "",
                                    contrasenya,
                                    descripcion ?: "",
                                    it)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    OutlinedButton(
                        enabled = enabledUpdate,
                        onClick = { coroutineScope.launch { viewModel.onUpdateSelected() } },
                        border = BorderStroke(1.dp, GiAPrimary),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = GiAPrimary,
                            containerColor = Color.Black
                        )
                    ) {
                        Text(text = "Guardar")
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (rol == "Estándar") {
                            OutlinedButton(
                                onClick = { showRoleFormulary = true },
                                border = BorderStroke(1.dp, GiAPrimary),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = GiAPrimary,
                                    containerColor = Color.Black
                                )
                            ) {
                                Text(text = "Solicitar rol científico")
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                        }


                        OutlinedButton(
                            onClick = { showEntryHistory = true },
                            border = BorderStroke(1.dp, GiAPrimary),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = GiAPrimary,
                                containerColor = Color.Black
                            )
                        ) {
                            Text(text = "Historial de entradas")
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    LogOutButton { coroutineScope.launch { viewModel.onLogOutSelected(navController) } }
                }
            }
        }
    }

    if (showRoleFormulary) {
        Dialog(onDismissRequest = { showRoleFormulary = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Explique sus motivos para solicitar el rol de científico",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    DataTextField(
                        text = "Descripción",
                        icon = Icons.Default.Description,
                        data = descripcionFormulario
                    ) {
                        viewModel.onFormularyChange(it)
                    }
                    HorizontalDivider(modifier = Modifier.height(8.dp))
                    Row {
                        CloseButton {
                            showRoleFormulary = false
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        SubmitButton (
                            onSubmit = {
                                showRoleFormulary = false
                                coroutineScope.launch { viewModel.onFormularySubmit() }
                            }
                        )
                    }
                }
            }
        }
    }

    if (showEntryHistory) {
        Dialog(onDismissRequest = { showRoleFormulary = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column (modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Historial de entradas",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (historialEntradas.faunaHistory.isNotEmpty()) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            items(historialEntradas.faunaHistory.size) { index ->
                                val item = historialEntradas.faunaHistory[index]
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(1.dp, Color.Gray)
                                ) {
                                    Column {
                                        Text(text = "Id: ${item.id}")
                                        Text(text = "Nombre: ${item.nombre}")
                                        Text(text = "Especie: ${item.especie}")
                                    }
                                }
                            }
                        }
                    } else {
                        Text("Aún no has creado animales")
                    }

                    if (historialEntradas.floraHistory.isNotEmpty()) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            items(historialEntradas.floraHistory.size) { index ->
                                val item = historialEntradas.floraHistory[index]
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(1.dp, Color.Gray)
                                ) {
                                    Column {
                                        Text(text = "Nombre: ${item.nombre}")
                                        Text(text = "Especie: ${item.especie}")
                                    }
                                }
                            }
                        }
                    } else {
                        Text("Aún no has creado plantas")
                    }

                    if (historialEntradas.artifactHistory.isNotEmpty()) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            items(historialEntradas.artifactHistory.size) { index ->
                                val item = historialEntradas.artifactHistory[index]
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(1.dp, Color.Gray)
                                ) {
                                    Column {
                                        Text(text = "Nombre: ${item.nombre}")
                                        Text(text = "Especie: ${item.grado}")
                                    }
                                }
                            }
                        }
                    } else {
                        Text("Aún no has creado ningún artefacto")
                    }

                    if (historialEntradas.delverHistory.isNotEmpty()) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            items(historialEntradas.delverHistory.size) { index ->
                                val item = historialEntradas.delverHistory[index]
                                val rangoNombre: String = when (item.rangoId) {
                                    1 -> "Cascabel"
                                    2 -> "Silbato rojo"
                                    3 -> "Silbato azul"
                                    4 -> "Silbato lunar"
                                    5 -> "Silbato negro"
                                    6 -> "Silbato blanco"
                                    else -> "Sin rango"
                                }
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(1.dp, Color.Gray)
                                ) {
                                    Column {
                                        Text(text = "Nombre: ${item.nombre}")
                                        Text(text = "Rango: $rangoNombre")
                                    }
                                }
                            }
                        }
                    } else {
                        Text("Aún no has creado ningún/a explorador/a")
                    }

                    CloseButton {
                        showEntryHistory = false
                    }
                }
            }
        }
    }

    if (successfulUpdate) {
        ResultDialog(
            "Perfil actualizado correctamente",
            "Los datos han sido actualizados satisfactoriamente",
            viewModel
        )
    }

    if (successfulFormularySubmit) {
        ResultDialog(
            "Solicitud enviada correctamente",
            "Su solicitud será revisada por un administrador",
            viewModel
        )
    }

    if (failedLogOut) {
        ResultDialog(
            "Cierre de sesión fallido",
            "No se ha podido cerrar sesión",
            viewModel
        )
    }

    if (failedUpdate) {
        ResultDialog(
            "Guardado de perfil fallido",
            "No se ha podido actualizar los datos de perfil",
            viewModel
        )
    }
}

@Composable
fun LogOutButton(onLogoutSelected: () -> Unit) {
    OutlinedButton(
        onClick = onLogoutSelected,
        border = BorderStroke(1.dp, GiAPrimary),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = GiAPrimary,
            containerColor = Color.Black
        )
    ) {
        Text(text = "Cerrar sesión")
    }
}

@Composable
fun ResultDialog(title: String, body: String, viewModel: ProfileViewModel) {
    AlertDialog(
        onDismissRequest = {},
        title = {Text(text = title)},
        text = {Text(text = body)},
        confirmButton = {
            Button(
                onClick = {viewModel.dismissDialog()}
            ) {
                Text("Ok")
            }
        }
    )
}