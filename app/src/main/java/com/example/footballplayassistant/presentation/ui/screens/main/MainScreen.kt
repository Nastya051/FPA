package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.CommonBottomBar
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.cards.MoneyCard
import com.example.footballplayassistant.presentation.customviews.headers.HeaderUser
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.theme.spacing
import com.example.footballplayassistant.viewmodels.EventsViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun MainScreen() {
    val navController = LocalNavController.current!!
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val viewModel: EventsViewModel = getViewModel()
    viewModel.getEvent(1)
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimary)) {
        LazyColumn(modifier = Modifier.fillMaxHeight(0.9f)) {
            item {
                HeaderUser(
                    name = "Alex",
                    onClickPlus = { navController.navigate(Route.CreateEventScreen.path) },
                    onClickBell = {/*notifications*/},
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.horizontal)
                )
            }

            item {
                MoneyCard(
                    money = 1500,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.horizontal)
                )
            }

            //если нет игр
            item {
                NoGames(
                    createEventClick = { navController.navigate(Route.CreateEventScreen.path) },
                    participateClick = {/*calendar*/})
            }
            //если есть игры
//            item {
//                AllButton(
//                    text = stringResource(id = R.string.mygames),
//                    onClick = { navController.navigate(Route.MyGamesScreen.withArgs(
//                        FilterCurrentArchive.Current.ordinal.toString())) },
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//                GameCard(
//                    place = "Арена Новый Футбол поле  Крылатское ",
//                    host = "Игорь Султанов",
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//            }

            item {
                Text(
                    text = stringResource(id = R.string.news),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.noNews),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 75.dp, bottom = 36.dp)
                )

                //если есть новости
//                AllButton(
//                    text = stringResource(id = R.string.news),
//                    onClick = { navController.navigate(Route.NewsScreen.path) },
//                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.horizontal)
//                )
//
//
//
//                HorizontalPager(state = pagerState) { page ->
//                    NewsCard(
//                        place = "Арена Новый Футбол поле  Крылатское", name = "Игорь Султанов",
//                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.horizontal)
//                    )
//                }
//                Row(
//                    Modifier
//                        .wrapContentHeight()
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                        .padding(bottom = 8.dp, top = 12.dp),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    repeat(pagerState.pageCount) { iteration ->
//                        val color =
//                            if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary
//                            else MaterialTheme.colorScheme.background
//                        Box(
//                            modifier = Modifier
//                                .padding(2.dp)
//                                .clip(RoundedCornerShape(2.dp))
//                                .background(color)
//                                .size(8.dp)
//                        )
//                    }
//                }
            }
        }
        CommonBottomBar()
    }
}

@Composable
private fun NoGames(createEventClick: () -> Unit = {}, participateClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.padding(top = 32.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.mygames),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 40.dp)
            )
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_ball_games_60),
                contentDescription = "Ball",
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = stringResource(id = R.string.noGames),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
        CommonButton(
            text = stringResource(R.string.addGame),
            onClick = createEventClick,
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
        )
        CommonButton(
            text = stringResource(R.string.participate),
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = participateClick,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
        )
    }
}