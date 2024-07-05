package com.example.footballplayassistant.presentation.customviews.dropdownmenus

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.customviews.textfields.TextFieldWithLeadingIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    placeholder: String,
    imStart: Int = 0,
    imTrail: Int = R.drawable.ic_arrow_menu_18_10,
    values: List<String>,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        if (imStart != 0)
            TextFieldWithLeadingIcon(
                placeholder = placeholder,
                value = selectedOptionText,
                imageStart = imStart,
                imageTrail = imTrail,
                color = color,
                modifier = modifier.menuAnchor()
            )
        else
            CommonTextField(
                placeholder = placeholder,
                value = selectedOptionText,
                imageStart = imStart,
                imageTrail = imTrail,
                color = color,
                modifier = modifier.menuAnchor()
            )

        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary),
            onDismissRequest = { expanded = false }
        ) {
            values.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption,
                            style = MaterialTheme.typography.labelLarge
                                .copy(fontWeight = FontWeight.W400)
                        )
                    },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onClick(selectedOptionText)
                    }
                )
            }
        }
    }
}