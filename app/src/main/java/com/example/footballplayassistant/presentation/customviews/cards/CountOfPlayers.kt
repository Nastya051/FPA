package com.example.footballplayassistant.presentation.customviews.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CountOfPlayers(currentPlayers: Int, maxPlayers: Int, modifier: Modifier = Modifier) {
    var color = MaterialTheme.colorScheme.secondary
    if (currentPlayers == maxPlayers)
        color = MaterialTheme.colorScheme.tertiaryContainer

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Text(
            text = "$currentPlayers/$maxPlayers",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
    }
}