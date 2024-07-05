package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.CommonBottomBar
import com.example.footballplayassistant.presentation.customviews.CommonSwitch
import com.example.footballplayassistant.presentation.customviews.cards.ActionsCard
import com.example.footballplayassistant.presentation.customviews.cards.CommonProfileGreenCard
import com.example.footballplayassistant.presentation.customviews.cards.PositionProfileGreenCard
import com.example.footballplayassistant.presentation.customviews.headers.HeaderUserProfile
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.enums.FilterCurrentArchive
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import kotlinx.coroutines.launch

@Composable
fun UserProfileScreen(isBackButton: Boolean = false) {
    val navController = LocalNavController.current!!
    val snackBarScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            HeaderUserProfile(modifier = Modifier.align(Alignment.TopCenter))

            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .align(Alignment.TopCenter)
                    .padding(top = 30.dp)
            ) {
                if (isBackButton)
                    HeaderWithBackButton(text = stringResource(id = R.string.profile),
                        colorText = MaterialTheme.colorScheme.onPrimary,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        onClickBack = {},
                        modifier = Modifier.padding(horizontal = 16.dp))
                else
                    Text(
                        text = stringResource(id = R.string.profile),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 25.dp)
                    )

                Box(
                    modifier = Modifier
                        .size(108.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unknown_user_foto),
                        contentDescription = "User foto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) {
                    item {
                        Text(
                            text = "name",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        Text(
                            text = "email",
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp)
                        )
                    }
                    item {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(bottom = 12.dp, top = 20.dp)
                        ) {
                            PositionProfileGreenCard(
                                position = "position",
                                modifier = Modifier.weight(0.5f)
                            )
                            CommonProfileGreenCard(
                                titleText = stringResource(id = R.string.bestPlayer),
                                icon = R.drawable.ic_medal_24,
                                blackText = "10",
                                greyText = "",
                                modifier = Modifier.weight(0.5f)
                            )
                        }
                    }
                    item {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            CommonProfileGreenCard(
                                titleText = stringResource(id = R.string.ratingHost),
                                icon = R.drawable.ic_star_24,
                                blackText = "4.5/5",
                                greyText = pluralStringResource(id = R.plurals.ratings, 5, 5),
                                modifier = Modifier.weight(0.5f)
                            )
                            CommonProfileGreenCard(
                                titleText = stringResource(id = R.string.attendance),
                                icon = R.drawable.ic_user_check_24,
                                blackText = "100%",
                                greyText = pluralStringResource(id = R.plurals.games, 5, 5),
                                modifier = Modifier.weight(0.5f)
                            )
                        }
                    }
                    item {
                        CommonSwitch(
                            text = stringResource(id = R.string.geolocationOn),
                            modifier = Modifier.padding(vertical = 32.dp)
                        )
                    }
                    item {
                        val str = stringResource(id = R.string.refCopied)
                        ActionsCard(
                            iconTextList = listOf(
                                Pair(
                                    R.drawable.ic_profile_black_24,
                                    stringResource(id = R.string.changeProfile)
                                ),
                                Pair(
                                    R.drawable.ic_wallet_24,
                                    stringResource(id = R.string.wallet)
                                ),
                                Pair(
                                    R.drawable.ic_calendar_22,
                                    stringResource(id = R.string.mygames)
                                ),
                                Pair(
                                    R.drawable.ic_subscribe_24,
                                    stringResource(id = R.string.subscriptions)
                                ),
                                Pair(
                                    R.drawable.ic_people_add_24,
                                    stringResource(id = R.string.inviteFriendCard)
                                )
                            ),
                            actionsList = listOf(
                                {navController.navigate(Route.ChangeProfileScreen.path) },
                                {navController.navigate(Route.WalletScreen.path) },
                                { navController.navigate(Route.MyGamesScreen.withArgs(FilterCurrentArchive.Current.ordinal.toString()))},
                                {navController.navigate(Route.SubscriptionsScreen.path) },
                                {
                                    snackBarScope.launch {
                                        snackbarHostState.showSnackbar(message = str)
                                    }
                                }),
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                    }

                    item {
                        ActionsCard(
                            iconTextList = listOf(
                                Pair(
                                    R.drawable.ic_document_24,
                                    stringResource(id = R.string.aboutApp)
                                ),
                                Pair(
                                    R.drawable.ic_lock_24,
                                    stringResource(id = R.string.safety)
                                ),
                            ),
                            actionsList = listOf(
                                { navController.navigate(Route.AboutAppScreen.path) },
                                { navController.navigate(Route.SafetyScreen.path) }),
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                    }
                }
            }
            CommonBottomBar(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

