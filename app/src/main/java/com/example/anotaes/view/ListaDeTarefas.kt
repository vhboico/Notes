package com.example.anotaes.view

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.anotaes.itemlista.TarefaItem
import com.example.anotaes.repositorio.TarefasRepositorio
import com.example.anotaes.ui.theme.Black
import com.example.anotaes.ui.theme.Purple700
import com.example.anotaes.ui.theme.White

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListaDeTarefas(navController: NavController) {

    val tarefasRepositorio = TarefasRepositorio()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                title = {
                    Text(
                        text = "Anotações",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            )
        },
        backgroundColor = Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("salvarTarefa") },
                contentColor = White,
                backgroundColor = Purple700,
                content = {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "", tint = White
                    )
                }
            )
        }
    ) {

        val listaTarefa =
            tarefasRepositorio.recuperarTarefas().collectAsState(mutableListOf()).value

        LazyColumn {
            itemsIndexed(listaTarefa) { position, _ ->
                TarefaItem(position = position, listaTarefa = listaTarefa, context = context, navController = navController)
            }
        }
    }

}