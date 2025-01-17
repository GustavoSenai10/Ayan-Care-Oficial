package br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.TesteHumorResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service.Exercicio
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service.Humor
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service.Sintoma
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.TesteHumorRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.components.CardMoodToday
import br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.components.Exercise
import br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.components.Symptoms
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HumorTestScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current

    var descricaoState by remember {
        mutableStateOf("")
    }

    var listTesteHumor_Humor by remember {
        mutableStateOf<List<Humor>>(emptyList())
    }

    var listTesteHumor_Sintoma by remember {
        mutableStateOf<List<Sintoma>>(emptyList())
    }

    var listTesteHumor_Exercicio by remember {
        mutableStateOf<List<Exercicio>>(emptyList())
    }


    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getTesteHumor().getTesteHumor()

    call.enqueue(object : Callback<TesteHumorResponse> {
        override fun onResponse(
            call: Call<TesteHumorResponse>,
            response: Response<TesteHumorResponse>
        ) {
            listTesteHumor_Humor = response.body()!!.opcoes.humores
            listTesteHumor_Sintoma = response.body()!!.opcoes.sintomas
            listTesteHumor_Exercicio = response.body()!!.opcoes.exercicios
        }

        override fun onFailure(call: Call<TesteHumorResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    fun testeHumor(
        observacao: String,
        id_paciente: Int,
        escolhas: List<String>,
    ) {

        val testeHumorRepository = TesteHumorRepository()
        lifecycleScope.launch {

            val response = testeHumorRepository.registerTesteHumor(
                observacao,
                id_paciente,
                escolhas
            )

            if (response.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "teste_humor bem-sucedido")
                Log.e("teste_humor", "teste_humor: ${response.body()}")
                val checagem = response.body()?.get("status")

                Log.e("teste_humor", "teste_humor: ${checagem}")

                if (checagem.toString() == "404") {
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()

                    navController.navigate("main_screen")
                }
            } else {
                val errorBody = response.errorBody()?.string()

                Log.e(MainActivity::class.java.simpleName, "Erro durante o teste_humor: $errorBody")
                Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
            }
        }

    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Teste de Humor",
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Bem vindo a tela de teste de humor.",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(400),
                color = Color(0xFF9E8BC1),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Como você está hoje?",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))

                //val items = listOf(listTesteHumor_Humor[0].resposta)
                var selectedItems by remember {
                    mutableStateOf(listOf<String>())
                }

                LazyRow() {
                    items(listTesteHumor_Humor) {
                        val isSelected = selectedItems.contains(it.resposta)
                        var selected by remember {
                            mutableStateOf(false)
                        }

                        CardMoodToday(
                            text = it.resposta,
                            selected = isSelected,
                            onClick = {
                                if (!selected) {
                                    selectedItems = selectedItems + it.resposta
                                    selected = true
                                } else {
                                    selectedItems = selectedItems.filter { item ->
                                        item != it.resposta
                                    }
                                    selected = false
                                }
                                Log.e("TAG", "HumorTestScreen: $selectedItems")
                            }
                        )

                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                Column {
                    Text(
                        text = "Praticou algum exercício?",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow() {
                        items(listTesteHumor_Exercicio) {

                            val isSelected = selectedItems.contains(it.exercicio)
                            var selected by remember {
                                mutableStateOf(false)
                            }

                            Exercise(
                                text = it.exercicio,
                                selected = isSelected,
                                onClick = {
                                    if (!selected) {
                                        selectedItems = selectedItems + it.exercicio
                                        selected = true
                                    } else {
                                        selectedItems = selectedItems.filter { item ->
                                            item != it.exercicio
                                        }
                                        selected = false
                                    }
                                    Log.e("TAG", "HumorTestScreen: $selectedItems")
                                }
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Sentiu algum sintoma?",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow() {
                    items(listTesteHumor_Sintoma) {

                        val isSelected = selectedItems.contains(it.sintoma)
                        var selected by remember {
                            mutableStateOf(false)
                        }

                        Symptoms(
                            text = it.sintoma,
                            selected = isSelected,
                            onClick = {
                                if (!selected) {
                                    selectedItems = selectedItems + it.sintoma
                                    selected = true
                                } else {
                                    selectedItems = selectedItems.filter { item ->
                                        item != it.sintoma
                                    }
                                    selected = false
                                }
                                Log.e("TAG", "HumorTestScreen: $selectedItems")
                            }
                        )

                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Text(
                        text = "Tem algo a acrescentar?",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF191D23)

                    )

                    OutlinedTextField(
                        value = descricaoState,
                        onValueChange = { descricaoState = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .border(
                                width = 1.dp,
                                color = Color(100, 116, 139),
                                shape = RoundedCornerShape(8.dp)
                            )

                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DefaultButton(
                        onClick = {
                            testeHumor(
                                observacao = descricaoState,
                                id_paciente = 44,
                                escolhas = selectedItems
                            )
                            Log.e("TAG", "HumorTestScreen: ${
                                testeHumor(
                                    observacao = descricaoState,
                                    id_paciente = 49,
                                    escolhas = selectedItems
                                )
                            }", )
                        },
                        text = "Enviar"
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Cancelar",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF35225F),
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate("main_screen")
                        }
                    )
                }


            }

        }
    }
}

//@Preview
//@Composable
//fun HumorTestScreenPreview() {
//    HumorTestScreen()
//}