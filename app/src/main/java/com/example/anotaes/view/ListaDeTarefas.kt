package com.example.anotaes.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.anotaes.itemlista.TarefaItem
import com.example.anotaes.ui.theme.Black
import com.example.anotaes.ui.theme.Purple700
import com.example.anotaes.ui.theme.White
import com.example.anotaes.viewmodel.TarefasViewModel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListaDeTarefas(
    navController: NavController,
    viewModel: TarefasViewModel = hiltViewModel()

) {

    val context = LocalContext.current

    val nomeUsuario = viewModel.perfilUsuario().collectAsState(initial = "").value

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
                },
                actions = {

                    Text(
                        text = nomeUsuario,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    TextButton(
                        onClick = {
                            val alertDialog = AlertDialog.Builder(context)
                            alertDialog.setTitle("Deseja mesmo sair?")
                            alertDialog.setMessage("Você precisa fazer o login novamente se desejar voltar")
                            alertDialog.setPositiveButton("Sim") { _, _ ->
                                FirebaseAuth.getInstance().signOut()
                                navController.navigate("login")
                            }
                            alertDialog.setNegativeButton("Não") { _, _ -> }
                                .show()
                        }
                    )
                    {
                        Text(
                            text = "Sair",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    }
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

        val listaTarefa = viewModel.recuperarTarefas().collectAsState(mutableListOf()).value

        LazyColumn {
            itemsIndexed(listaTarefa) { position, _ ->
                TarefaItem(
                    position = position,
                    listaTarefa = listaTarefa,
                    context = context,
                    navController = navController,
                    viewModel
                )
            }
        }
    }
}