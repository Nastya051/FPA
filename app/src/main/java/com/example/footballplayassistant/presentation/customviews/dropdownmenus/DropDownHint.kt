package com.example.footballplayassistant.presentation.customviews.dropdownmenus

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R

@Composable
fun DropDownHint(expand: MutableState<Boolean>, text: String) {
    MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(12.dp))) {
        DropdownMenu(
            expanded = expand.value,
            onDismissRequest = { expand.value = false },
            modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(12.dp))
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.primary,
                            lineHeight = 14.5.sp
                        ),
                    )
                },
                onClick = { expand.value = false }
            )
        }
    }
}