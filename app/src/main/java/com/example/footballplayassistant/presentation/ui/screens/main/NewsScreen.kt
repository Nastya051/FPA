package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.CommonBottomBar
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.buttons.SelectionButtons
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.enums.FilterFriendsPlaces
import com.example.footballplayassistant.presentation.enums.getFiltersFriendsPlaces
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
@Preview
fun NewsScreen() {
    val navController = LocalNavController.current!!
    val filtersList = FilterFriendsPlaces.entries.toList()
    val filterButton = remember { mutableIntStateOf(filtersList[0].ordinal) }

    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.Top
    ) {
        Column(modifier = Modifier.fillMaxHeight(0.9f)) {
            HeaderWithBackButton(
                text = stringResource(id = R.string.news),
                imageButton = R.drawable.ic_plus_24,
                onClickBack = { navController.navigate(Route.MainScreen.path) },
                onClickOther = { navController.navigate(Route.CreateEventScreen.path) },
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp)
            )

            SelectionButtons(
                valueList = getFiltersFriendsPlaces(),
                selectedItemIndex = filterButton.intValue,
                onSelected = { filterButton.intValue = it },
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
            )

            //геолокация отключена
//            GeolocationOff()

            //геолокация включена, новостей нет
            if (filterButton.intValue == FilterFriendsPlaces.Friends.ordinal) {
                NoNewsFriends()
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    NoNewsPlaces()
                    CommonButton(
                        text = stringResource(R.string.addToFavourite),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
            }

//есть новости

//            LazyColumn(
//                modifier = Modifier
//                    .padding(horizontal = MaterialTheme.spacing.horizontal)
//            ) {
//                if (filterButton.value == 0) {
//                    item {
//                        NewsCard(
//                            place = "Москва, ул. Ленинский проспект, строение 2 корпус 3",
//                            name = "name",
//                            address = "Москва, ул. Ленинский проспект, строение 2 корпус 3",
//                            modifier = Modifier.padding(bottom = 10.dp)
//                        )
//                    }
//                    item {
//                        NewsCard(
//                            place = "place",
//                            name = "name",
//                            address = "Москва, ул. Ленинский проспект, строение 2 корпус 3",
//                            modifier = Modifier.padding(bottom = 10.dp)
//                        )
//                    }
//                } else {
//                    item {
//                        GameCard(
//                            place = "Москва, ул. Ленинский проспект, строение 2 корпус 3",
//                            host = "name",
//                            address = "Москва, ул. Ленинский проспект, строение 2 корпус 3",
//                            modifier = Modifier.padding(bottom = 10.dp)
//                        )
//                    }
//                }
//            }
        }
        CommonBottomBar()
    }
}

@Composable
private fun NoNewsFriends() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.noNewsTitle),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.noNewsFriens),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NoNewsPlaces() {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.noNewsTitle),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.noNewsPlaces),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun GeolocationOff() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.geolocationOff),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = stringResource(id = R.string.geolocationText),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center
            )
        }
        CommonButton(
            text = stringResource(R.string.geolocationOn),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}