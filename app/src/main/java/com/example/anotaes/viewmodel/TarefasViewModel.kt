package com.example.anotaes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anotaes.model.Tarefa
import com.example.anotaes.repositorio.TarefasRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TarefasViewModel @Inject constructor(private val tarefasRepositorio: TarefasRepositorio): ViewModel() {

    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    fun salvarTarefa(title: String, descricao: String, nivel: Int, checkState: Boolean){
        viewModelScope.launch {
            tarefasRepositorio.salvarTarefa(title, descricao, nivel, checkState)
        }
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        viewModelScope.launch {
            tarefasRepositorio.recuperarTarefas().collect{
                _todasTarefas.value = it
            }
        }
        return todasTarefas
    }

    fun deletarTarefa(tarefa: String){
        viewModelScope.launch {
            tarefasRepositorio.deletarTarefa(tarefa)
        }
    }

    fun updateCheck(tarefa: String, checkState: Boolean){
        viewModelScope.launch {
            tarefasRepositorio.updateCheck(tarefa, checkState)
        }
    }
}