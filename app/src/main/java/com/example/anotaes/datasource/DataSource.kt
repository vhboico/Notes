package com.example.anotaes.datasource

import com.example.anotaes.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    fun salvarTarefa(title: String, descricao: String, nivel: Int) {

        val taretaMap = hashMapOf(
            "title" to title,
            "descricao" to descricao,
            "nivel" to nivel
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

    fun deletarTarefa(tarefa: String){
        db.collection("tarefa").document(tarefa).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
}