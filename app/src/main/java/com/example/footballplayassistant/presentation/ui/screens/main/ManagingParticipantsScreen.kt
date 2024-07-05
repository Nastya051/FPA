package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.PlayerInTeam
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.rows.TeamsAndCountPlayers
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun ManagingParticipantsScreen() {
    val navController = LocalNavController.current!!

    Scaffold(topBar = {
        Column {
            HeaderWithBackButton(
                text = stringResource(id = R.string.managingParticipants),
                onClickBack = { navController.navigate(Route.MatchScreen.path) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )

            TeamsAndCountPlayers(
                maxPlayers = 10,
                currentPlayersFirstTeam = 0,
                currentPlayersSecondTeam = 0,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    },
        bottomBar = {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
            Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)) {
                CommonButton(
                    text = stringResource(id = R.string.inviteParticipants),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }) {
        //нет участников
        NoPlayers(modifier = Modifier.padding(it))

        //есть участники
//        ListPlayers(
//            firstTeam = listOf(1, 2, 3),
//            secondTeam = listOf(2, 2, 2, 2),
//            modifier = Modifier.padding(it))
    }
}

@Composable
private fun NoPlayers(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 33.dp), verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.noPlayers),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        Text(
            text = stringResource(id = R.string.youCanInvite),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ListPlayers(
    firstTeam: List<Int>,
    secondTeam: List<Int>,
    modifier: Modifier = Modifier
) {
    val showDialog = remember{ mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.deletePlayerQuestion),
        description = stringResource(id = R.string.negativeRate),
        whiteButton = stringResource(id = R.string.delete),
        bottomButton = stringResource(id = R.string.cancel),
        image = R.drawable.ic_warning_93,
        onClickWhite = { /*delete player*/ },
        onClickBottom = { showDialog.value = false },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayerInTeam(
                        userTag = "@user1245",
                        position = "вратарь, хост",
                        foto = R.drawable.user_foto,
                        isBorder = true
                    )
                    for (i in firstTeam.indices)
                        PlayerInTeam(
                            userTag = "@user1245",
                            position = "вратарь",
                            foto = R.drawable.user_foto,
                            deleteIcon = true,
                            onDelete = {showDialog.value = true}
                        )
                }
                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (i in secondTeam.indices)
                        PlayerInTeam(
                            userTag = "@user12345",
                            position = "вратарь",
                            foto = R.drawable.user_foto,
                            deleteIcon = true,
                            onDelete = {showDialog.value = true}
                        )
                }
            }
        }
    }
}