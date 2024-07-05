package com.example.footballplayassistant.presentation.customviews.checkboxes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R


@Composable
fun CommonCheckBoxAgree(onClick: (Boolean) -> Unit = {}){
    val state = remember { mutableStateOf(false) }
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Checkbox(
            checked = state.value,
            onCheckedChange = { state.value = it
                              onClick(it)},
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondary,
                uncheckedColor = MaterialTheme.colorScheme.secondary
            )
        )
        Text(
            text = stringResource(id = R.string.agree),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        )
    }
}