package com.example.footballplayassistant.presentation.customviews.checkboxes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.presentation.customviews.rows.UserFotoForList

@Composable
fun CheckBoxFriend(
    text: String,
    name: String,
    foto: Int,
    onClick: (Boolean) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val state = remember { mutableStateOf(false) }
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserFotoForList(
            text = text, name = name, foto = foto,
            Modifier.fillMaxWidth(0.8f)
        )
        Checkbox(
            checked = state.value,
            onCheckedChange = {
                state.value = it
                onClick(state.value)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondary,
                uncheckedColor = MaterialTheme.colorScheme.secondary,),
            modifier = Modifier.fillMaxWidth(0.2f)
        )
    }
}