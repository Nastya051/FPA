package com.example.footballplayassistant.presentation.customviews.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun PlayersCard(modifier: Modifier = Modifier, participants: List<String>, maxPlayers: Int) {
    val navController = LocalNavController.current!!

    Column(modifier = modifier.padding(top = 32.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ParticipantsText()
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            GridImages(participants.size - 3)
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.playersWithout),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    CountOfPlayers(currentPlayers = participants.size, maxPlayers = maxPlayers)
                }
                HorizontalDivider(
                    thickness = 1.dp, color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Row {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = participants.joinToString(
                                separator = ", ",
                                limit = 3,
                                truncated = ""),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = stringResource(id = R.string.andOther,
                                participants.size - 3),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    RoundButton(
                        onClick = { navController.navigate(Route.MatchParticipantsScreen.path) },
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                }
            }
        }
    }
}

@Composable
fun PlayersHostCard(modifier: Modifier = Modifier, nameHost: String, participants: List<String>) {
    val navController = LocalNavController.current!!

    Column(modifier = modifier.padding(top = 32.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ParticipantsText()
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            GridImages(participants.size - 3)
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = nameHost,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.host),
                            style = MaterialTheme.typography.displaySmall
                                .copy(fontWeight = FontWeight.W500),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                R.drawable.ic_arrow_green_next_16),
                            contentDescription = "Arrow next",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp, color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Row {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = participants.joinToString(
                                separator = ", ",
                                limit = 3,
                                truncated = ""),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = stringResource(id = R.string.andOther,
                                participants.size - 3),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    RoundButton(
                        onClick = { navController.navigate(Route.MatchParticipantsScreen.path) },
                        containerColor = MaterialTheme.colorScheme.secondary,
                        tintColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                }
            }
        }
    }
}

@Composable
fun NoOneJoinedCard(modifier: Modifier = Modifier, maxPlayers: Int) {
    val navController = LocalNavController.current!!

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ParticipantsText()
            CountOfPlayers(currentPlayers = 0, maxPlayers = maxPlayers)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.noOneJoin),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W600),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundButton(
                onClick = { navController.navigate(Route.MatchParticipantsScreen.path) },
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}

@Composable
fun OnlyHostJoinedCard(modifier: Modifier = Modifier, maxPlayers: Int, name: String) {
    val navController = LocalNavController.current!!

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(top = 32.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ParticipantsText()
            CountOfPlayers(currentPlayers = 1, maxPlayers = maxPlayers)
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ImageForGrid()
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(horizontal = 8.dp),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.host),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_green_next_16),
                    contentDescription = "Arrow next",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp, color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.noOneMoreJoin),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundButton(
                onClick = { navController.navigate(Route.MatchParticipantsScreen.path) },
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}

@Composable
private fun ImageForGrid() {
    Image(
        painter = painterResource(id = R.drawable.unknown_user_foto),
        contentDescription = "User foto",
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .size(44.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
    )
}

@Composable
private fun BoxForGrid(count: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .size(44.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            text = stringResource(id = R.string.plus, count),
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun GridImages(count: Int) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(end = 14.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            ImageForGrid()
            ImageForGrid()
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            ImageForGrid()
            BoxForGrid(count)
        }
    }
}

@Composable
private fun RoundButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    tintColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(containerColor = containerColor),
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrows_24),
            contentDescription = "Arrows",
            tint = tintColor
        )
    }
}

@Composable
private fun ParticipantsText() {
    Text(
        text = stringResource(id = R.string.players),
        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
        color = MaterialTheme.colorScheme.primary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(bottom = 16.dp),
    )
}