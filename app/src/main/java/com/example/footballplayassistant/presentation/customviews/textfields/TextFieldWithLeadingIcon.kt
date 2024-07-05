package com.example.footballplayassistant.presentation.customviews.textfields

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithLeadingIcon(
    placeholder: String,
    imageTrail: Int = 0,
    imageStart: Int = 0,
    keyBoard: KeyboardType = KeyboardType.Email,
    value: String = "",
    color: Color = MaterialTheme.colorScheme.onPrimary,
    isError: Boolean = false,
    onTrailClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val textValue = remember { mutableStateOf("") }

    val icon by remember { mutableIntStateOf(imageTrail) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = onTrailClick,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Trail icon"
            )
        }
    }

    TextField(
        value = if (value.isEmpty()) textValue.value else value,
        onValueChange = { textValue.value = it },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(60.dp),
        textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = color,
            focusedContainerColor = color,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,

            errorContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.15f),
            errorCursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorIndicatorColor = MaterialTheme.colorScheme.outlineVariant
        ),
        isError = isError,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        trailingIcon = { if (imageTrail != 0) trailingIconView() },
        leadingIcon = {
            Icon(
                painter = painterResource(id = imageStart),
                contentDescription = "Image start"
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyBoard),
    )
}