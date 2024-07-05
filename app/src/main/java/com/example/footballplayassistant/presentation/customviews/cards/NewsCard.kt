package com.example.footballplayassistant.presentation.customviews.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.rows.BottomRowDateTimeMoney
import com.example.footballplayassistant.presentation.customviews.rows.FotoAndNameForCard
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun NewsCard(
    place: String,
    name: String,
    modifier: Modifier = Modifier,
    address: String = "",
    distance: Int = 0
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp)
        ) {
            Row {
                FotoAndNameForCard(
                    text = stringResource(id = R.string.host),
                    name = name,
                    foto = R.drawable.user_foto,
                    modifier = Modifier.weight(0.85f)
                )
                CountOfPlayers(
                    currentPlayers = 10, maxPlayers = 10, modifier = Modifier
                        .weight(0.15f)
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f)
                ) {
                    Text(
                        text = place,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                        maxLines = 2
                    )
                    if (address.isNotEmpty()) {
                        Text(
                            text = address,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_location_24),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "${distance}км",
                        style = MaterialTheme.typography.bodyMedium
                            .copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = 12.dp
                )
            )

            BottomRowDateTimeMoney(date = "27.08.10", time = "12:00", price = "700")
        }
    }
}