package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.SelectionButtons
import com.example.footballplayassistant.presentation.customviews.cards.GameCard
import com.example.footballplayassistant.presentation.customviews.cards.OldGameCard
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.enums.getFiltersCurrentArchive
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun MyGamesScreen(filter: Int) {
    val navController = LocalNavController.current!!
    val filterButton = remember { mutableIntStateOf(filter) }

    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Top
    ) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.mygames),
            onClickBack = { navController.navigate(Route.MainScreen.path) },
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)
        )

        SelectionButtons(
            valueList = getFiltersCurrentArchive(),
            selectedItemIndex = filterButton.intValue,
            onSelected = { filterButton.intValue = it },
            modifier = Modifier.padding(all = 16.dp)
        )
        //нет игр
        NoGames()

        //есть игры
//        if (filterButton.intValue == FilterCurrentArchive.Current.ordinal)
//            CurrentGames(currentGames = listOf("first card", "second card", "third card"))
//        else
//            ArchiveGames(
//                resultGames = listOf("first card", "second card", "third card"),
//                oldGames = listOf("first card", "second card", "third card")
//            )
    }
}

@Composable
private fun CurrentGames(currentGames: List<String>) {
    LazyColumn {
        items(currentGames.size) {
            GameCard(
                place = currentGames[it],
                host = currentGames[it],
                distance = 100,
                cardColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun ArchiveGames(resultGames: List<String>, oldGames: List<String>) {
    LazyColumn {
        if (resultGames.isNotEmpty()) {
            item {
                Text(
                    text = stringResource(id = R.string.results),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                        fontWeight = FontWeight.W500
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                )
            }
            items(resultGames.size) {
                GameCard(
                    place = resultGames[it],
                    host = "",
                    distance = 50,
                    cardColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        if (oldGames.isNotEmpty()) {
            item {
                Text(
                    text = stringResource(id = R.string.history),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                        fontWeight = FontWeight.W500
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 16.dp,
                            top = 32.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                )
            }
            items(oldGames.size) {
                OldGameCard(
                    place = oldGames[it],
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun NoGames() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.noGamesYet),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.youHaventAnyGames),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}