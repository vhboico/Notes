package com.example.anotaes.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anotaes.ui.theme.ShapesEdit
import com.example.anotaes.ui.theme.White
import com.example.anotaes.ui.theme.dark_blue
import com.example.anotaes.ui.theme.dark_pink

@Composable
fun ButtonAuth(
    onClick: () -> Unit,
    text: String
) {

    OutlinedButton(
        onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(50.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        dark_pink,
                        dark_blue
                    )
                ),
                shape = ShapesEdit.medium
            ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = ShapesEdit.medium,
        border = BorderStroke(2.dp, White)
    ) {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = White)
    }
}

@Composable
@Preview
fun ButtonPreview() {
    ButtonAuth(onClick = {}, "Concluir")
}