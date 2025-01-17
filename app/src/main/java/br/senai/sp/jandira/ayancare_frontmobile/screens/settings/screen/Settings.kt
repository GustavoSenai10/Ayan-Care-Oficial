package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.components.CardSettings
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.funcaoQueChamaSqlLite.deleteUserSQLite
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import kotlin.math.log

@Composable
fun SettingsScreen(
    navController: NavController,
    navRotasController: NavController
) {

    val context = LocalContext.current

    val dados = PacienteRepository(context = context).findUsers()

    var id = 0

    var array = Paciente()

    if(dados.isNotEmpty()){
        array = dados[0]

        id = array.id.toInt()
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            //horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = { navController.navigate("main_screen") }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Text(
                    text = "Configurações",
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF090A0A),
                    textAlign = TextAlign.Center
                )
            }
            //CONTA
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Conta",
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFF090A0A)
            )

            Spacer(modifier = Modifier.height(30.dp))


            CardSettings(
                imageVector = Icons.Default.SupervisedUserCircle,
                text = "Responsável",
                onClick = {
                    navRotasController.navigate("responsible_screen")
                }
            )
            CardSettings(
                imageVector = Icons.Default.PersonOutline,
                text = "Contas Vinculadas",
                onClick = {
                    navRotasController.navigate("linked_accounts_screen")
                }
            )
            CardSettings(
                imageVector = Icons.Default.Lock,
                text = "Código do Paciente",
                onClick = {
                    navRotasController.navigate("codigo_paciente_screen")
                }
            )
            CardSettings(
                imageVector = Icons.Default.NotificationsNone,
                text = "Notificações",
                onClick = {}
            )

            //MAIS
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Mais",
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFF090A0A)
            )

            Spacer(modifier = Modifier.height(30.dp))

            CardSettings(
                imageVector = Icons.Default.StarOutline,
                text = "Desvincular conta",
                onClick = {
                    navController.navigate("emergencia_screen")
                }
            )
            CardSettings(
                imageVector = Icons.Default.ThumbUpOffAlt,
                text = "Sugestões",
                onClick = {
                    navRotasController.navigate("sugestoes_screen")
                }
            )


            Row (
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom
            ){
                Text(
                    text = "Sair",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            deleteUserSQLite(context = context, id.toInt())
                            navController.navigate("login_screen")
                        },
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF979C9E),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

//@Preview
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen()
//}