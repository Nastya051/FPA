package com.example.footballplayassistant.presentation.customviews.dropdownmenus

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonDropDownMenu(
    placeholder: String,
    imStart: Int = 0,
    imTrail: Int = R.drawable.ic_arrow_menu_18_10,
    values: List<String>,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    onClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Button(
            onClick = { /*onClick.invoke(selectedOptionText)*/ },
            colors = ButtonDefaults.buttonColors(containerColor = containerColor),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            modifier = modifier
                .menuAnchor()
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (imStart != 0)
                        Image(
                            imageVector = ImageVector.vectorResource(imStart),
                            contentDescription = "Image start",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    Text(
                        text = if (selectedOptionText.isEmpty()) placeholder else selectedOptionText,
                        color = if (selectedOptionText.isEmpty()) textColor
                        else MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                        )
                }
                Image(
                    imageVector = ImageVector.vectorResource(imTrail),
                    contentDescription = "Image trail"
                )
            }
        }

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
                                .copy(fontWeight = FontWeight.W400))
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