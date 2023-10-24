package com.example.anotaes.datasource

import com.example.anotaes.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataSource @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    fun salvarTarefa(title: String, descricao: String, nivel: Int, checkState: Boolean) {

        val taretaMap = hashMapOf(
            "title" to title,
            "descricao" to descricao,
            "nivel" to nivel,
            "checkState" to checkState
        )

        db.collection("tarefa").document(title).set(taretaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {

        val listaTarefa: MutableList<Tarefa> = mutableListOf()

        db.collection("tarefa").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (documento in it.result) {
                    val tarefa = documento.toObject(Tarefa::class.java)
                    listaTarefa.add(tarefa)
                    _todasTarefas.value = listaTarefa
                }
            }
        }
        return todasTarefas
    }

    fun deletarTarefa(tarefa: String) {
        db.collection("tarefa").document(tarefa).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun updateCheck(tarefa: String, checkState: Boolean) {
        db.collection("tarefas").document(tarefa).update("checkState", checkState)
            .addOnCompleteListener {

            }.addOnFailureListener {

        }
    }
}