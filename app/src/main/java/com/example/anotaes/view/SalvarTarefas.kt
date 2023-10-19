package com.example.anotaes.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.anotaes.componentes.Botao
import com.example.anotaes.componentes.CaixaDeTexto
import com.example.anotaes.constantes.Constantes
import com.example.anotaes.repositorio.TarefasRepositorio
import com.example.anotaes.ui.theme.Purple700
import com.example.anotaes.ui.theme.White
import com.example.anotaes.ui.theme.green_select
import com.example.anotaes.ui.theme.green_unselected
import com.example.anotaes.ui.theme.red_select
import com.example.anotaes.ui.theme.red_unselect
import com.example.anotaes.ui.theme.yellow_select
import com.example.anotaes.ui.theme.yellow_unselect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SalvarTarefas(navController: NavController) {

    var titulo by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }

    val semPrioridade by remember {
        mutableStateOf(false)
    }

    var prioridadeBaixa by remember {
        mutableStateOf(false)
    }

    var prioridadeMedia by remember {
        mutableStateOf(false)
    }

    var prioridadeAlta by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val tarefasRepositorio = TarefasRepositorio()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                title = {
                    Text(
                        text = "Salvar Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CaixaDeTexto(
                value = titulo,
                onValueChanged = { titulo = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 10.dp),
                label = "Escreve o título",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            CaixaDeTexto(
                value = descricao,
                onValueChanged = { descricao = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
                    .height(150.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Nível de prioridade",
                    fontSize = 15.sp,
                    color = Color.Black
                )

                RadioButton(
                    selected = prioridadeBaixa,
                    onClick = {
                        prioridadeBaixa = !prioridadeBaixa
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = green_select,
                        unselectedColor = green_unselected,
                    )
                )

                RadioButton(
                    selected = prioridadeMedia,
                    onClick = {
                        prioridadeMedia = !prioridadeMedia
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = yellow_select,
                        unselectedColor = yellow_unselect,
                    )
                )

                RadioButton(
                    selected = prioridadeAlta,
                    onClick = {
                        prioridadeAlta = !prioridadeAlta
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = red_select,
                        unselectedColor = red_unselect,
                    )
                )
            }

            Botao(
                onClick = {

                    var mensagem = true

                    scope.launch(Dispatchers.IO) {
                        if (titulo.isEmpty()) {
                            mensagem = false
                        } else if (titulo.isNotEmpty() && descricao.isNotEmpty() && prioridadeBaixa) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.PRIORIDADE_BAIXA
                            )
                            mensagem = true
                        } else if (titulo.isNotEmpty() && descricao.isNotEmpty() && prioridadeMedia) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.PRIORIDADE_MEDIA
                            )
                            mensagem = true
                        } else if (titulo.isNotEmpty() && descricao.isNotEmpty() && prioridadeAlta) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.PRIORIDADE_ALTA
                            )
                            mensagem = true
                        } else if (titulo.isNotEmpty() && descricao.isNotEmpty() && semPrioridade) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.SEM_PRIORIDADE
                            )
                            mensagem = true
                        } else if (titulo.isNotEmpty() && prioridadeBaixa) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.PRIORIDADE_BAIXA
                            )
                            mensagem = true
                        } else if (titulo.isNotEmpty() && prioridadeMedia) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.PRIORIDADE_MEDIA
                            )
                            mensagem = true
                        } else if (titulo.isNotEmpty() && prioridadeAlta) {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.PRIORIDADE_ALTA
                            )
                            mensagem = true
                        } else {
                            tarefasRepositorio.salvarTarefa(
                                titulo,
                                descricao,
                                Constantes.SEM_PRIORIDADE
                            )
                            mensagem = true
                        }
                    }

                    scope.launch(Dispatchers.Main) {
                        if (mensagem) {
                            Toast.makeText(context, "Sucesso ao salvar", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Erro ao salvar", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                label = "Salvar"
            )
        }
    }
}