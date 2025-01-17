package br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile.screens.cadastro.components.ProgressBar

@Composable
fun PatientAddressScreen(
    navController: NavController
){

    Surface (
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Finalizar Cadastro",
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "Preencha os dados restantes para finalizar seu cadastro.",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9E8BC1),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    DefaultTextField(
                        valor = "",
                        label = "CPF",
                        onValueChange = {}
                    ) {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(
                        valor = "",
                        label = "Estado",
                        onValueChange = {}
                    ) {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(
                        valor = "",
                        label = "Cidade",
                        onValueChange = {}
                    ) {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(
                        valor = "",
                        label = "Bairro",
                        onValueChange = {}
                    ) {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(
                        valor = "",
                        label = "Logradouro",
                        onValueChange = {}
                    ) {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(
                        valor = "",
                        label = "Número",
                        onValueChange = {}
                    ) {}

                }
            }

            Column (
                modifier = Modifier.width(190.dp)
            ) {
                DefaultButton(
                    text = "Continuar",
                    onClick = { navController.navigate("add_disease_screen") }
                )
            }
            ProgressBar(text = "3 / 3", valor = 248)

        }
    }

}