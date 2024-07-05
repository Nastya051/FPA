package com.example.footballplayassistant.presentation.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.screens.search_tab.Player
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun BestPlayerScreen() {
    var checked by remember { mutableIntStateOf(-1) }
    val players = listOf(
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Егор", "Дружин", R.drawable.player_example),
        Player("Серявгей", "Сергеев", R.drawable.player_example_1),
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Игорь", "Султанов", R.drawable.loadexample),
    )
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)

    ) {
        HeaderWithBackButton(text = stringResource(R.string.BestPlayerScreenHeader), modifier = Modifier.padding(MaterialTheme.spacing.medium))
        LazyColumn {
            items(players.size) { i ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.horizontal,
                            vertical = MaterialTheme.spacing.small
                        )
                ) {
                    PlayerWithCheckboxTagCard(
                        tag = "@tag",
                        name = "${players[i].name} ${players[i].surname}",
                        image = players[i].photo,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Checkbox(
                        checked = checked == i,
                        onCheckedChange = {
                            checked = i
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.secondary,
                            uncheckedColor = MaterialTheme.colorScheme.secondary,
                        ),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            CommonButton(stringResource(R.string.confirmButtonText), enable = checked != -1)
            Text(
                stringResource(R.string.skipButtonText), style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}