package com.example.anotaes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anotaes.listener.ListenerAuth
import com.example.anotaes.repositorio.AuthRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepositorio: AuthRepositorio): ViewModel() {

    fun cadastro(nome: String, email: String, senha: String, listenerAuth: ListenerAuth){
        viewModelScope.launch {
            authRepositorio.cadastro(nome, email, senha, listenerAuth)
        }
    }

    fun login(email: String, senha: String, listenerAuth: ListenerAuth){
        viewModelScope.launch {
            authRepositorio.login(email, senha, listenerAuth)
        }
    }

    fun verificarUsuarioLogado(): Flow<Boolean>{
        return authRepositorio.verificarUsuarioLogado()
    }
}