package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CardMedicine
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfile

@Composable
fun ProfileScreen(
    navController: NavController,
    navRotasController: NavController
) {

    val scrollState = rememberScrollState()

    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(end = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ){
                Button(
                    onClick = {
                        navRotasController.navigate("edit_profile_screen")
                    },
                    modifier = Modifier
                        .width(105.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(Color(249, 241, 237))
                ) {
                    Text(
                        text = "Editar perfil",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                    .fillMaxSize()
            ) {
                CircleProfile(
                    painter = painterResource(id = R.drawable.instrucao3)
                )
                Text(
                    text = "Daniela Pinto",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000)
                )
                Text(
                    text = "Paciente",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Tratamentos",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )

//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(minSize = 50.dp),
//            ) {
//                items(6) {
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Card(
//                        modifier = Modifier
//                            .padding(bottom = 10.dp, end = 10.dp),
//                        shape = RoundedCornerShape(size = 16.dp)
//                    ) {
//                        ProcessingProfile()
//                    }
//                }
//            }

                Spacer(modifier = Modifier.height(5.dp))

                LazyRow() {
                    items(4) {
                        ProcessingProfile()
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Remédios",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

    }
}

//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen()
//}