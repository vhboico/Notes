package com.example.anotaes.datasource

import com.example.anotaes.listener.ListenerAuth
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class Auth @Inject constructor() {

    val auth = FirebaseAuth.getInstance()

    val db = FirebaseFirestore.getInstance()

    val _verificarUsuarioLogado = MutableStateFlow(false)
    val verificarUsuarioLogado: StateFlow<Boolean> = _verificarUsuarioLogado

    fun cadastro(nome: String, email: String, senha: String, listenerAuth: ListenerAuth) {

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            listenerAuth.onFailure("Preencha todos os campos")
        } else {
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {

                    val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

                    val userMap = hashMapOf(
                        "Nome" to nome,
                        "Email" to email,
                        "userID" to userID
                    )
                    db.collection("users").document(userID).set(userMap).addOnCompleteListener {
                        listenerAuth.onSucess("Sucesso ao cadastrar usuário", "login")
                    }.addOnFailureListener {
                        listenerAuth.onFailure("Erro inesperado")
                    }
                }
            }.addOnFailureListener { exception ->
                val erro = when (exception) {
                    is FirebaseAuthUserCollisionException -> "Está conta já foi cadastrada"
                    is FirebaseAuthWeakPasswordException -> "A senha precisa conter pelo menos 6 caracteres"
                    is FirebaseNetworkException -> "Sem conexão com a internet"
                    else -> "Email inválido"
                }
                listenerAuth.onFailure(erro)
            }
        }
    }

    fun login(email: String, senha: String, listenerAuth: ListenerAuth) {

        if (email.isEmpty() || senha.isEmpty()) {
            listenerAuth.onFailure("Preencha todos os campos")
        } else {
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                if (it.isSuccessful) {
                    listenerAuth.onSucess("Login concluído", "listaTarefas")
                }
            }.addOnFailureListener { exception ->

                val erro = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "Senha incorreta"
                    is FirebaseNetworkException -> "Sem conexão com a internet"
                    else -> "E-mail inválido"
                }

                listenerAuth.onFailure(erro)
            }
        }
    }

    fun verificarUsuarioLogado(): Flow<Boolean>{

        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        _verificarUsuarioLogado.value = usuarioLogado != null
        return verificarUsuarioLogado
    }

}