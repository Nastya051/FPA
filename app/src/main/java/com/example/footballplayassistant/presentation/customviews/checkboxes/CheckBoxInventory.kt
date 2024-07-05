package com.example.footballplayassistant.presentation.customviews.checkboxes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun CheckBoxInventory(onClick: (List<Boolean>) -> Unit = {}) {
    val state = remember { mutableStateOf(false) }
    val state2 = remember { mutableStateOf(false) }
    val state3 = remember { mutableStateOf(false) }
    val statesList = mutableListOf(false, false, false)
    Column {
        Row {
            Checkbox(
                checked = state.value,
                onCheckedChange = {
                    if (state3.value) {
                        state3.value = false
                    }
                    state.value = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.secondary,
                    uncheckedColor = MaterialTheme.colorScheme.secondary
                )
            )
            Text(
                text = stringResource(id = R.string.manish),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Row {
            Checkbox(
                checked = state2.value,
                onCheckedChange = {
                    if (state3.value) {
                        state3.value = false
                    }
                    state2.value = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.secondary,
                    uncheckedColor = MaterialTheme.colorScheme.secondary
                )
            )
            Text(
                text = stringResource(id = R.string.ball),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Row {
            Checkbox(
                checked = state3.value,
                onCheckedChange = {
                    if (state.value || state2.value) {
                        state.value = false
                        state2.value = false
                    }
                    state3.value = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.secondary,
                    uncheckedColor = MaterialTheme.colorScheme.secondary
                )
            )
            Text(
                text = stringResource(id = R.string.noInventory),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }

    LaunchedEffect(state.value, state2.value, state3.value) {
        statesList[0] = state.value
        statesList[1] = state2.value
        statesList[2] = state3.value
        onClick(statesList)
    }
}
