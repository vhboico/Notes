package com.example.anotaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anotaes.view.ListaDeTarefas
import com.example.anotaes.view.SalvarTarefas
import com.example.anotaes.viewmodel.TarefasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val viewModel: TarefasViewModel = hiltViewModel()
            
            NavHost(navController = navController, startDestination = "listaTarefas"){
                composable("listaTarefas"){
                    ListaDeTarefas(navController, viewModel)
                }
                composable("salvarTarefa"){
                    SalvarTarefas(navController, viewModel)
                }
            }
        }
    }
}
