package com.example.footballplayassistant.presentation.customviews.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.rows.BottomRowDateTimeMoney
import com.example.footballplayassistant.presentation.customviews.rows.FotoAndNameForCard
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun GameCard(
    place: String,
    host: String,
    address: String = "",
    distance: Int = 0,
    cardColor: Color = MaterialTheme.colorScheme.primaryContainer,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current!!
    Card(
        onClick = { navController.navigate(Route.MatchScreen.path) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.horizontal)) {
            if (host.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.youhost),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
                )
                Row(modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)) {
                    Text(
                        text = place,
                        style = MaterialTheme.typography.labelLarge
                            .copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.85f)
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
            } else {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = place,
                            style = MaterialTheme.typography.displayMedium
                                .copy(fontWeight = FontWeight.W500),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 2,
                            modifier = Modifier.weight(0.75f)
                        )
                        Row(
                            modifier = Modifier.weight(0.25f),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_location_24),
                                contentDescription = "Location",
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
                    if (address.isNotEmpty()) {
                        Text(
                            text = address,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .fillMaxWidth(0.75f)
                        )
                    }
                }
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    FotoAndNameForCard(
                        text = host,
                        name = host,
                        foto = R.drawable.user_foto,
                        modifier = Modifier.weight(0.85f)
                    )
                    CountOfPlayers(
                        currentPlayers = 10,
                        maxPlayers = 10,
                        modifier = Modifier
                            .weight(0.15f)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            BottomRowDateTimeMoney(date = "27.08.10", time = "12:00", price = "700")
        }
    }
}