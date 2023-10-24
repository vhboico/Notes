package com.example.anotaes.repositorio

import com.example.anotaes.datasource.DataSource
import com.example.anotaes.model.Tarefa
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TarefasRepositorio @Inject constructor(private val dataSource: DataSource) {

    fun salvarTarefa(title: String, descricao: String, nivel: Int, checkState: Boolean){
        dataSource.salvarTarefa(title, descricao, nivel, checkState)
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }

    fun deletarTarefa(tarefa: String){
        dataSource.deletarTarefa(tarefa)
    }

    fun updateCheck(tarefa: String, checkState: Boolean){
        dataSource.updateCheck(tarefa, checkState)
    }

    fun perfilUsuario(): Flow<String>{
        return dataSource.perfilUsuario()
    }
}