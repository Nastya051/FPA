package com.example.footballplayassistant.presentation.ui.screens.main

import android.os.Build
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.MatchFotoBox
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.cards.CommentsCard
import com.example.footballplayassistant.presentation.customviews.cards.CommonIconTextCard
import com.example.footballplayassistant.presentation.customviews.cards.CommonIconTextInventoryCard
import com.example.footballplayassistant.presentation.customviews.cards.CommonOtherInfoCard
import com.example.footballplayassistant.presentation.customviews.cards.GreenBorderCard
import com.example.footballplayassistant.presentation.customviews.cards.NoOneJoinedCard
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.CommonActionsMenu
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.rows.FieldNameRow
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.skydoves.cloudy.Cloudy
import kotlinx.coroutines.launch

@Composable
fun MatchScreen() {
    val navController = LocalNavController.current!!
    val showDialogTakePart = remember { mutableStateOf(false) }
    val showDialogCancel = remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    val blur = remember { mutableStateOf(false) }

    val snackBarScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    DialogScreen(
        header = stringResource(id = R.string.whoPlays),
        description = stringResource(id = R.string.youGetNotify2),
        greenButton = stringResource(id = R.string.playYourself),
        whiteButton = stringResource(id = R.string.callFriends),
        bottomButton = stringResource(id = R.string.cancel),
        onClickGreen = { navController.navigate(Route.ChooseTeamScreen.path) },
        onClickWhite = { navController.navigate(Route.InviteFriendsScreen.path) },
        onClickBottom = { showDialogTakePart.value = false },
        onDismissRequest = { showDialogTakePart.value = false },
        showDialog = showDialogTakePart.value
    )

    DialogScreen(
        header = stringResource(id = R.string.cancelParticipationQuestion),
        description = stringResource(id = R.string.negativeRate),
        whiteButton = stringResource(id = R.string.cancelParticipation),
        bottomButton = stringResource(id = R.string.cancel),
        image = R.drawable.ic_warning_93,
        onClickWhite = { navController.navigate(Route.MainScreen.path) },
        onClickBottom = { showDialogCancel.value = false },
        onDismissRequest = { showDialogCancel.value = false },
        showDialog = showDialogCancel.value
    )

    Scaffold(containerColor = MaterialTheme.colorScheme.onPrimary,
        bottomBar = {
            //событие закончилось
            //R.string.returnMain
            //R.string.rateGame
            //R.string.markParticipants
//            BottomButtonsEventEnd(
//                textButton = R.string.returnMain,
//                onClick = { navController.navigate(Route.MainScreen.path) })

            //нельзя управлять игрой
//            BottomButtonsCantManageGame()

            //нельзя редактировать
//            BottomButtonsCantEditGame()

            //отменить + депозит
//            BottomButtonsCancel(bottomText = false, autoReturn = false, onClick = {showDialogCancel.value=true})

            //участвовать
            CommonBottomButtons(
                textButton = R.string.participate,
                authorized = true,
                deleted = true,
                onClick = { showDialogTakePart.value = true })

            //редактировать
//            CommonBottomButtons(
//                textButton = R.string.editGame,
//                authorized = true,
//                deleted = false,
//                onClick = { navController.navigate(Route.EditGameScreen.path) })


        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        topBar = {
            val str = stringResource(id = R.string.refCopied)
            HeaderWithBackButton(
                text = stringResource(id = R.string.match),//R.string.matchIsOver
                imageButton = R.drawable.ic_arrow_share_25,
                onClickBack = { navController.navigate(Route.MainScreen.path) },
                onClickOther = { expanded.value = true },
                actionsMenu = {
                    CommonActionsMenu(
                        expand = expanded,
                        actions = listOf(R.string.whatsapp, R.string.copyInvitation),
                        onClicks = listOf(
                            { /*whatsapp*/ },
                            {
                                snackBarScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = str
                                    )
                                }
                            })
                    )
                },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)
            )
        }) {
        if (blur.value)
            if (Build.VERSION.RELEASE.toDouble() < 12.0)
                Box(modifier = Modifier.padding(it)) {
                    Cloudy(radius = 20) {
                        Content()
                    }
                    Text(
                        text = stringResource(id = R.string.matchIsOverBlur),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.W400),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp)
                    )
                }
            else
                Box(modifier = Modifier.padding(it)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(radius = 20.dp)
                            .align(Alignment.Center)
                    ) {
                        Content()
                    }
                    Text(
                        text = stringResource(id = R.string.matchIsOverBlur),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.W400),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp)
                    )
                }
        else
            Content(modifier = Modifier.padding(it))
    }
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current!!

    LazyColumn(modifier = modifier) {
        item {
            MatchFotoBox(
                currentPlayers = 5, maxPlayers = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }

        item { FieldNameRow(fieldName = "Футбольный манеж 38х16 «Спорт Ангар» Теплый стан ") }

        item {
            Text(
                text = "Москва, ул. Ленинский проспект, строение 2 корпус 3 ул. Ленинский проспект, строение 2",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 22.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(0.9f)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                GreenBorderCard(
                    text = stringResource(id = R.string.cost), value = "1500₽",
                    modifier = Modifier.weight(0.3f)
                )
                GreenBorderCard(
                    text = stringResource(id = R.string.sexMatch), value = "M",
                    modifier = Modifier.weight(0.3f)
                )
                GreenBorderCard(
                    text = stringResource(id = R.string.type),
                    value = "открытый",
                    modifier = Modifier.weight(0.3f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp, bottom = 11.dp),
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                CommonIconTextCard(
                    icon = R.drawable.ic_time_black_24,
                    text = "08:00 - 12:00",
                    modifier = Modifier.weight(1f)
                )

                CommonIconTextCard(
                    icon = R.drawable.ic_calendar_22,
                    text = "01.01.2021",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(intrinsicSize = IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                CommonIconTextInventoryCard(modifier = Modifier.weight(1f))

                CommonOtherInfoCard(
                    onClick = { navController.navigate(Route.MatchInfoScreen.path) },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                )
                //для хоста
//                        CommonWithdrawalSumCard(
//                            summa = 1000,
//                            onClick = {},
//                            modifier = Modifier
//                                .weight(1f)
//                                .fillMaxHeight()
//                                .align(Alignment.CenterVertically)
//                        )
            }
        }

        item {
            //никто не присоединился
            NoOneJoinedCard(
                maxPlayers = 10,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            //присоединился только хост
//                    OnlyHostJoinedCard(
//                        maxPlayers = 10,
//                        name = "Name name",
//                        modifier = Modifier.padding(horizontal = 16.dp)
//                    )

            //игроки присоединились
//                    PlayersHostCard(modifier = Modifier.padding(horizontal = 16.dp),
//                        participants = listOf(
//                            "Игорь Архипов",
//                            "Игорь Архипов",
//                            "Игорь Архипов",
//                            "Игорь Архипов",
//                            "Игорь Архипов"
//                        ),
//                        nameHost = "Игорь Архипов")

//                    PlayersCard(
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        participants = listOf(
//                            "Игорь Архипов",
//                            "Игорь Архипов",
//                            "Игорь Архипов",
//                            "Игорь Архипов",
//                            "Игорь Архипов"
//                        ),
//                        maxPlayers = 10
//                    )
        }

        item {
            //если игрок не присоединился
//            if (Build.VERSION.RELEASE.toDouble() < 12.0)
//                Box {
//                    Cloudy(radius = 20) {
//                        CommentsCard(commentsList = listOf())
//                    }
//                    Text(
//                        text = stringResource(id = R.string.joinToGameForSeeMsg),
//                        style = MaterialTheme.typography.labelMedium.copy(
//                            fontWeight = FontWeight.W400
//                        ),
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .padding(horizontal = 16.dp)
//                    )
//                }
//            else
//                Box {
//                    Box(
//                        modifier = Modifier
//                            .blur(radius = 20.dp)
//                            .align(Alignment.Center)
//                    ) {
//                        CommentsCard(commentsList = listOf())
//                    }
//                    Text(
//                        text = stringResource(id = R.string.joinToGameForSeeMsg),
//                        style = MaterialTheme.typography.labelMedium.copy(
//                            fontWeight = FontWeight.W400
//                        ),
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .padding(horizontal = 16.dp)
//                    )
//                }
            //если присоединился
            CommentsCard(commentsList = listOf())
        }
    }
}

@Composable
private fun BottomButtonsCancel(bottomText: Boolean, autoReturn: Boolean, onClick: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.deposit),
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.onSecondaryContainer),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "1500p",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onPrimaryContainer),
                        textAlign = TextAlign.Center
                    )
                }
            }
            CommonButton(
                text = stringResource(id = R.string.cancelParticipate),
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = { onClick() }
            )
        }
        if (bottomText)
            Text(
                text = stringResource(id = if (autoReturn) R.string.autoReturn else R.string.notAutoReturn),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
    }
}

@Composable
private fun BottomButtonsEventEnd(textButton: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonButton(text = stringResource(id = textButton),
            onClick = { onClick() })
        Text(
            text = stringResource(id = R.string.eventEnd),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSecondaryContainer),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun BottomButtonsCantManageGame() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonButton(
            text = stringResource(id = R.string.gameManagement),
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = stringResource(id = R.string.youCantEditGame),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSecondaryContainer),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun BottomButtonsCantEditGame() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonButton(
            text = stringResource(id = R.string.editGame),
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = stringResource(id = R.string.youCantEditGame),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSecondaryContainer),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CommonBottomButtons(
    textButton: Int,
    authorized: Boolean,
    deleted: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonButton(
            text = stringResource(id = textButton),
            containerColor = if (deleted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
            contentColor = if (deleted) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.primary,
            onClick = { if (!deleted) onClick() }
        )
        //если не авторизован
        if (!authorized)
            Text(
                text = stringResource(id = R.string.signInForTakePart),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 16.dp)
                    .fillMaxWidth()
            )
        if (deleted)
            Text(
                text = stringResource(id = R.string.youWasDeleted),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 12.dp)
                    .fillMaxWidth()
            )
    }
}