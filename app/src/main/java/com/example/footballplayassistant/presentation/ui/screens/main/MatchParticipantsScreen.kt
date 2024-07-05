package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.PlayerInTeam
import com.example.footballplayassistant.presentation.customviews.cards.CountOfPlayers
import com.example.footballplayassistant.presentation.customviews.rows.TeamsAndCountPlayers
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun MatchParticipantsScreen() {
    val navController = LocalNavController.current!!

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.matchParticipants),
            onClickBack = { navController.navigate(Route.MatchScreen.path) },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TeamsAndCountPlayers(
                    maxPlayers = 10,
                    currentPlayersFirstTeam = 3,
                    currentPlayersSecondTeam = 8
                )
            }

            item {
                Row(modifier = Modifier.padding(top = 39.dp)) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (i in 0..2)
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
                        for (i in 0..7)
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