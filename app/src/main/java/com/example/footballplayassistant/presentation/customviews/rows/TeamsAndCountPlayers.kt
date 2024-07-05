package com.example.footballplayassistant.presentation.customviews.rows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.cards.CountOfPlayers

@Composable
fun TeamsAndCountPlayers(
    maxPlayers: Int,
    currentPlayersFirstTeam: Int,
    currentPlayersSecondTeam: Int,
    modifier: Modifier=Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.weight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.lightTeam),
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CountOfPlayers(
                currentPlayers = currentPlayersFirstTeam,
                maxPlayers = maxPlayers,
                modifier = Modifier.width(60.dp)
            )
        }
        Column(
            modifier = Modifier.weight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.darkTeam),
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CountOfPlayers(
                currentPlayers = currentPlayersSecondTeam,
                maxPlayers = maxPlayers,
                modifier = Modifier.width(60.dp)
            )
        }
    }
}