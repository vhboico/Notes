package com.example.anotaes.componentes

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.anotaes.ui.theme.White
import com.example.anotaes.ui.theme.blue_light

@Composable
fun Botao(
    onClick: () -> Unit,
    modifier: Modifier,
    label: String
){

    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = blue_light
        )
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = White
            )
    }
}
