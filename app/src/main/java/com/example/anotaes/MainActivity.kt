package com.example.anotaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anotaes.view.ListaDeTarefas
import com.example.anotaes.view.SalvarTarefas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            
            NavHost(navController = navController, startDestination = "listaTarefas"){
                composable("listaTarefas"){
                    ListaDeTarefas(navController)
                }
                composable("salvarTarefa"){
                    SalvarTarefas(navController)
                }
            }
        }
    }
}
