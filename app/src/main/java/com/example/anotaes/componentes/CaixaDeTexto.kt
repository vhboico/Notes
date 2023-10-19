package com.example.anotaes.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.anotaes.ui.theme.Black
import com.example.anotaes.ui.theme.ShapesEdit
import com.example.anotaes.ui.theme.White
import com.example.anotaes.ui.theme.blue_light

@Composable
fun CaixaDeTexto(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
) {

    OutlinedTextField(
        value = value,
        onValueChanged,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Black,
            focusedBorderColor = blue_light,
            focusedLabelColor = blue_light,
            backgroundColor = White,
            cursorColor = blue_light,
        ),
        shape = ShapesEdit.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}
