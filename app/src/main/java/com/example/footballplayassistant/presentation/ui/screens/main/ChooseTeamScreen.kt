package com.example.footballplayassistant.presentation.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.PlayerInTeam
import com.example.footballplayassistant.presentation.customviews.cards.CountOfPlayers
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.rows.TeamsAndCountPlayers
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
@Preview
fun ChooseTeamScreen() {
    val navController = LocalNavController.current!!
    Scaffold(containerColor = MaterialTheme.colorScheme.onPrimary,
        bottomBar = {
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.tertiary)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RoundButton(
                    enable = true,
                    onClick = { navController.navigate(Route.PaymentScreen.path) },
                    modifier = Modifier.weight(0.4f)
                )
                RoundButton(enable = false, modifier = Modifier.weight(0.4f))
            }
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderWithBackButton(
                text = "name",
                onClickBack = { navController.navigate(Route.MatchScreen.path) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.teamsCanChanged),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(vertical = 14.dp)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_warning_12),
                            contentDescription = "Warning",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(4.dp)
                        )
                    }
                }

                item {
                    TeamsAndCountPlayers(
                        maxPlayers = 10,
                        currentPlayersFirstTeam = 5,
                        currentPlayersSecondTeam = 10
                    )
                }

                item {
                    Row(modifier = Modifier.padding(top = 24.dp)) {
                        Column(
                            modifier = Modifier.weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            for (i in 0..5)
                                PlayerInTeam(
                                    userTag = "@user1245",
                                    position = "вратарь",
                                    foto = R.drawable.user_foto
                                )
                        }
                        Column(
                            modifier = Modifier.weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            for (i in 0..10)
                                PlayerInTeam(
                                    userTag = "@user12345",
                                    position = "вратарь",
                                    foto = R.drawable.user_foto
                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RoundButton(
    enable: Boolean,
    onClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = { onClick.invoke() },
            enabled = enable,
            modifier = Modifier.border(
                width = 1.dp,
                color = if (enable) MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.tertiary,
                shape = CircleShape
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_plus_24),
                contentDescription = "",
                tint = if (enable) MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.tertiary
            )
        }
        Text(
            text = if (enable) stringResource(id = R.string.join)
            else stringResource(id = R.string.noEmpty),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}