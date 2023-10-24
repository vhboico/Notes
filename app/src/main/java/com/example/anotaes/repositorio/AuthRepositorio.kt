package com.example.anotaes.repositorio

import com.example.anotaes.datasource.Auth
import com.example.anotaes.listener.ListenerAuth
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AuthRepositorio @Inject constructor(private val auth: Auth) {

    fun cadastro(nome: String, email: String, senha: String, listenerAuth: ListenerAuth){
        auth.cadastro(nome, email, senha, listenerAuth)
    }
}