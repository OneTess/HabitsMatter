package com.example.habitsmatter.uiElements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.habitsmatter.R
import com.example.habitsmatter.statics.Constants

// TODO: Properly implement font styles using Material3
@Composable
fun CustomTextField(
    value: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    onValueChange:(String) -> Unit,
    placeholder: String
) {
    val textColorPrimary = colorResource(id = R.color.text_primary)

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, fontSize = fontSize) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Constants.paddingMedium,
            ),
        textStyle = TextStyle.Default.copy(
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.transparent),
            unfocusedContainerColor = colorResource(id = R.color.transparent),
            disabledContainerColor = colorResource(id = R.color.transparent),
            errorContainerColor = colorResource(id = R.color.transparent),

            focusedIndicatorColor = colorResource(id = R.color.transparent),
            unfocusedIndicatorColor = colorResource(id = R.color.transparent)
        ),
    )
}