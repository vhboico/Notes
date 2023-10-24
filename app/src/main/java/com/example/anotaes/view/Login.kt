package com.example.anotaes.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.anotaes.R
import com.example.anotaes.componentes.ButtonAuth
import com.example.anotaes.ui.theme.Black
import com.example.anotaes.ui.theme.Purple700
import com.example.anotaes.ui.theme.ShapesEdit
import com.example.anotaes.ui.theme.White
import com.example.anotaes.ui.theme.dark_blue
import com.example.anotaes.ui.theme.dark_pink

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Login(navController: NavController) {

    Scaffold(
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    dark_pink,
                    dark_blue
                )
            )
        ),
        backgroundColor = Color.Transparent
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            var email by remember {
                mutableStateOf("")
            }

            var password by remember {
                mutableStateOf("")
            }

            var visibilityPassword by remember {
                mutableStateOf(true)
            }
            var message by remember {
                mutableStateOf("")
            }

            val icon = if (visibilityPassword)
                painterResource(id = R.drawable.baseline_visibility_24)
            else {
                painterResource(id = R.drawable.baseline_visibility_off_24)
            }

            Icon(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.baseline_task_24),
                contentDescription = "",
                tint = White
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(text = "Email")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                    textColor = Black,
                    cursorColor = Purple700,
                    focusedBorderColor = Purple700,
                    focusedLabelColor = Purple700
                ),
                shape = ShapesEdit.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = ""
                    )
                },
                maxLines = 1
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "Senha")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                    textColor = Black,
                    cursorColor = Purple700,
                    focusedBorderColor = Purple700,
                    focusedLabelColor = Purple700
                ),
                shape = ShapesEdit.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                trailingIcon = {
                    IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                        Icon(
                            painter = icon,
                            contentDescription = ""
                        )
                    }
                },
                maxLines = 1,
                visualTransformation = if (visibilityPassword) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            Text(
                text = message,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = White
            )

            ButtonAuth(onClick = { navController.navigate("listaTarefas") }, text = "Login")

            Spacer(modifier = Modifier.padding(20.dp))

            TextButton(
                onClick = {
                    navController.navigate("signUp")
                }
            ) {
                Text(
                    text = "Não tem conta? Cadastre-se agora",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = White)
            }

            Spacer(modifier = Modifier.padding(bottom = 20.dp))

        }
    }
}