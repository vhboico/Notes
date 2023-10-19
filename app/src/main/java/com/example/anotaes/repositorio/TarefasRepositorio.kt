package com.example.anotaes.repositorio

import com.example.anotaes.datasource.DataSource
import com.example.anotaes.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepositorio() {

    private val dataSource = DataSource()

    fun salvarTarefa(title: String, descricao: String, nivel: Int){
        dataSource.salvarTarefa(title, descricao, nivel)
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }

    fun deletarTarefa(tarefa: String){
        dataSource.deletarTarefa(tarefa)
    }
}