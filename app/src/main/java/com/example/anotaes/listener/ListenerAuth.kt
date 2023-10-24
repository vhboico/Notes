package com.example.anotaes.listener

interface ListenerAuth {

    fun onSucess(mensagem: String, tela: String)
    fun onFailure(erro: String)
}