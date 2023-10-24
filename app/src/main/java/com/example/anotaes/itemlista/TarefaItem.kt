package com.example.anotaes.itemlista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.anotaes.R
import com.example.anotaes.model.Tarefa
import com.example.anotaes.ui.theme.Black
import com.example.anotaes.ui.theme.Shapes
import com.example.anotaes.ui.theme.ShapesEdit
import com.example.anotaes.ui.theme.White
import com.example.anotaes.ui.theme.green_select
import com.example.anotaes.ui.theme.red_select
import com.example.anotaes.ui.theme.yellow_select
import com.example.anotaes.viewmodel.TarefasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    position: Int,
    listaTarefa: MutableList<Tarefa>,
    context: Context,
    navController: NavController,
    viewModel: TarefasViewModel = hiltViewModel()
) {

    val tituloTarefa = listaTarefa[position].title
    val descricaoTarefa = listaTarefa[position].descricao
    val nivelPrioridade = listaTarefa[position].nivel
    val tarefaConcluida = listaTarefa[position].checkState

    val scope = rememberCoroutineScope()

    var isCheck by remember {
        mutableStateOf(tarefaConcluida)
    }

    fun alertDialog() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar")
            .setMessage("Tem certeza que deseja apagar a tarefa?")
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deletarTarefa(tituloTarefa.toString())

                scope.launch(Dispatchers.Main) {
                    listaTarefa.removeAt(position)
                    navController.navigate("listaTarefas")
                    Toast.makeText(context, "Sucesso ao deletar", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não") { _, _ ->

            }
            .show()
    }

    val txtPrioridade: String = when (nivelPrioridade) {
        0 -> {
            "Sem prioridade"
        }

        1 -> {
            "Prioridade baixa"
        }

        2 -> {
            "Prioridade média"
        }

        else -> {
            "Prioridade alta"
        }
    }

    val color = when (nivelPrioridade) {

        0 -> {
            Color.Black
        }

        1 -> {
            green_select
        }

        2 -> {
            yellow_select
        }

        else -> {
            red_select
        }
    }

    Card(
        backgroundColor = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (titulo, descricao, prioridade, card, delete, check) = createRefs()

            Text(
                text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(titulo) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(parent.top)
                },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Black
            )

            Text(
                text = descricaoTarefa.toString(),
                modifier = Modifier.constrainAs(descricao) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(titulo.bottom, 10.dp)
                },
                color = Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = txtPrioridade,
                modifier = Modifier.constrainAs(prioridade) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(descricao.bottom, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                },
                color = Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Card(
                backgroundColor = color,
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(card) {
                        start.linkTo(prioridade.end, 10.dp)
                        top.linkTo(descricao.bottom, 10.dp)
                        bottom.linkTo(parent.bottom, 10.dp)
                    },
                shape = Shapes.large
            ) {

            }

            IconButton(onClick = {
                alertDialog()
            },
                modifier = Modifier.constrainAs(delete) {
                    start.linkTo(parent.start, 250.dp)
                    top.linkTo(descricao.bottom, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                }
            )
            {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                    contentDescription = null,
                )
            }

            Checkbox(
                checked = isCheck!!,
                onCheckedChange = {
                    isCheck = it

                    scope.launch(Dispatchers.IO) {
                        if (isCheck!!) {
                            viewModel.updateCheck(tituloTarefa!!, true)
                        } else {
                            viewModel.updateCheck(tituloTarefa!!, false)
                        }
                    }
                },
                modifier = Modifier.constrainAs(check) {
                    start.linkTo(delete.end)
                    top.linkTo(descricao.bottom, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = green_select,
                    uncheckedColor = Black
                )
            )
        }
    }
}
