package com.example.footballplayassistant.presentation.customviews.textfields

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CommonTextField(
    placeholder: String,
    imageTrail: Int = 0,
    imageStart: Int = 0,
    singleLine: Boolean = true,
    keyBoard: KeyboardType = KeyboardType.Email,
    isPassword: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    cornerRadius: Dp = 60.dp,
    maxLength: Int = 40,
    value: String = "",
    readOnly: Boolean = false,
    isError: Boolean = false,
    tintIcon: Color = MaterialTheme.colorScheme.primary,
    mask: VisualTransformation = VisualTransformation.None,
    onClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val textValue = remember { mutableStateOf("") }

    var icon by remember { mutableIntStateOf(imageTrail) }
    var isPass by remember { mutableStateOf(isPassword) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (icon == R.drawable.ic_eye_slash_24) {
                    icon = R.drawable.ic_eye_24
                    isPass = false
                } else if (icon == R.drawable.ic_eye_24) {
                    icon = R.drawable.ic_eye_slash_24
                    isPass = true
                }
            },
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Trail icon",
                tint = tintIcon
            )
        }
    }
    val source = remember { MutableInteractionSource() }

    BasicTextField(
        value = if (value.isEmpty()) textValue.value else value,
        onValueChange = {
            if (it.length <= maxLength) {
                textValue.value = it
            }
            onClick.invoke(textValue.value)
        },
        textStyle = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.primary
        ),
        singleLine = true,
        interactionSource = source,
        modifier = modifier
            .fillMaxWidth()
            .border(
                color = if (isError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onPrimary,
                width = 1.dp,
                shape = RoundedCornerShape(cornerRadius)
            ),
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoard),
        visualTransformation = if (isPass) PasswordVisualTransformation()
        else mask,
    )
    {
        TextFieldDefaults.DecorationBox(
            value = if (value.isEmpty()) textValue.value else value,
            innerTextField = { it() },
            enabled = true,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = source,
            isError = isError,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            },
            trailingIcon = { if (imageTrail != 0) trailingIconView() },
            shape = RoundedCornerShape(cornerRadius),
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
            contentPadding = PaddingValues(
                vertical = 12.dp,
                horizontal = 16.dp
            ),
        )
    }
}