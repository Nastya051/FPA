package com.example.footballplayassistant.presentation.customviews.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.presentation.customviews.rows.BottomRowDateTimeMoney
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun OldGameCard(
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colorScheme.onPrimary,
    place: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.horizontal)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp)
            ) {
                Text(
                    text = place,
                    style = MaterialTheme.typography.labelMedium
                        .copy(fontWeight = FontWeight.W400),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2,
                    modifier = Modifier.weight(0.75f)
                )
                CountOfPlayers(
                    currentPlayers = 10,
                    maxPlayers = 10,
                    modifier = Modifier
                        .weight(0.15f)
                        .align(Alignment.CenterVertically)
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
            )

            BottomRowDateTimeMoney(date = "27.08.10", time = "12:00", price = "700")
        }
    }
}