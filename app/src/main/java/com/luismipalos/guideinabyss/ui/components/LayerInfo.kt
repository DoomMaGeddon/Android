package com.luismipalos.guideinabyss.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luismipalos.guideinabyss.R

@Composable
fun LayerInfo(numCapa: Int) {
    when (numCapa) {
        1 -> Layer1()
        2 -> Layer2()
        3 -> Layer3()
        4 -> Layer4()
        5 -> Layer5()
        6 -> Layer6()
        7 -> Layer7()
    }
}

@Composable
fun Layer1() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Primera capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer1),
            contentDescription = "Capa 1",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp),
        )
        Text(text = "Borde del Abismo",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)
        Text(text = """
            Profundidad: 0 ~ 1.350 metros.
            Efectos de ascenso: Nauseas, mareos ligeros.
            Descripción: La sección del abismo más somera, justo bajo la ciudad de Orth. El clima es consistentemente soleado y calmado. Gran parte de la fauna de esta capa es inofensiva para el ser humano. Es hogar de varios esqueletos rezando repartidos por la capa.
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato rojo")
    }
}

@Composable
fun Layer2() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Segunda capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer2),
            contentDescription = "Capa 2",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = "Bosque de la Tentación")
        Text(text = """
            Profundidad: 1.350 ~ 2.600 metros.
            Efectos de ascenso: Nauseas y dolores de cabeza intensos, insensibilidad de las extremidades.
            Descripción: La primera sección verdaderamente peligrosa del Abismo. La fauna es mucho más peligrosa, y la flora se compone principalmente de árboles. Existe una zona conocida como el "Bosque Invertido", donde los árboles crecen al revés y el viento ascendente sopla con fuerza, incluso el agua fluye hacia arriba. Aquí reside el puesto de avanzada explorador llamado "Campamento Buscador", o "Seeker Camp".
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato azul")
    }
}

@Composable
fun Layer3() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Tercera capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer3),
            contentDescription = "Capa 3",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = "Gran Falla")
        Text(text = """
            Profundidad: 2.600 ~ 7.000 metros.
            Efectos de ascenso: Los mismos que en la segunda capa, mas alucinaciones visuales y auditivas.
            Descripción: Un acantilado completamente vertical y de forma cilíndrica. Existe una inmensa red de cuevas en las paredes. Habitan depredadores aéreos, como el Crimson Splitjaw. La flora es muy escasa.
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato lunar")
    }
}

@Composable
fun Layer4() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cuarta capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer4),
            contentDescription = "Capa 4",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = "Los Cálices de los Gigantes")
        Text(text = """
            Profundidad: 7.000 ~ 12.000 metros.
            Efectos de ascenso: Dolor intenso por todo el cuerpo, sangrado por cada orificio.
            Descripción: Una extraja y muy húmeda jungla con flora que captura el agua del aire. El clima es muy húmedo y constantemente neblinoso. La fauna es mucho más rica, conteniendo algunas especies especialmente peligrosas. Existe un campo de Fortunas Eternas cerca de los 9.000 metros.
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato negro")
    }
}

@Composable
fun Layer5() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Quinta capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer5),
            contentDescription = "Capa 5",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = "Mar de Cadáveres")
        Text(text = """
            Profundidad: 12.000 ~ 13.000 metros.
            Efectos de ascenso: Privación completa de los sentidos, confusión, comportamiento autolesivo.
            Descripción: La capa del Abismo más corta con diferencia, aunque la más ancha. El clima es húmedo y frío, únicamente volviéndose seco cerca de Ido Front. Gran parte de la fauna es marina, debido al gran mar que cubre la mayor parte de la capa. Es hogar de Ido Front, la última base de operaciones humana de la que se puede volver con vida. También es hogar de uno de los depredadores más peligrosos del Abismo conocido.
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato negro, en compañía de un silbato blanco")
    }
}

@Composable
fun Layer6() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Sexta capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer6),
            contentDescription = "Capa 6",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = "La Capital sin Retorno")
        Text(text = """
            Profundidad: 13.000 ~ 15.500 metros.
            Efectos de ascenso: Pérdida de la humanidad, posibilidad de muerte.
            Descripción: Esta capa es conocida por ser imposible de ascender sin extremas consecuencias. El clima es muy caluroso, y la presencia de explosiones geotérmicas provocan liberaciones de gases venenosos y lluvias de metal fundido. Gran parte de la fauna de esta capa es desconocida, aunque existen animales de tamaños titánicos y extremadamente peligrosos. Es hogar de la princesa de los Narehates, Faputa.
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato blanco")
    }
}

@Composable
fun Layer7() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Séptima capa",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.layer7),
            contentDescription = "Capa 7",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = "La Vorágine Final")
        Text(text = """
            Profundidad: 15.500 ~ ? metros.
            Efectos de ascenso: Muerte certera (presuntamente).
            Descripción: Muy poco se conoce de esta capa. Apenas unos pocos Silbatos Blancos han conseguido llegar tan lejos. Se rumorea la existencia de un anillo pivotal en la entrada de la capa. Se rumorea de la existencia de fauna inconcebiblemente peligrosa, además de unas criaturas llamadas "Gatekeepers" cerca del anillo pivotal.
        """.trimIndent())
        Text(text = "Rango requerido para acceder: Silbato blanco")
    }
}