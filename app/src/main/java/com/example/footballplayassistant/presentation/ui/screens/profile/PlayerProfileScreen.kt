package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.cards.CommonProfileGreenCard
import com.example.footballplayassistant.presentation.customviews.cards.GameCard
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.CommonActionsMenu
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.DropDownHint
import com.example.footballplayassistant.presentation.customviews.headers.HeaderUserProfile
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun PlayerProfileScreen() {
    val navController = LocalNavController.current!!
    val buttonEnable = remember { mutableStateOf(false) }
    val expandedHint = remember { mutableStateOf(false) }
    val expandedMenu = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.doneWithPoint),
        description = stringResource(id = R.string.userBlockedSuccess),
        greenButton = stringResource(id = R.string.toMain),
        whiteButton = stringResource(id = R.string.toSubscriptions),
        onClickGreen = { navController.navigate(Route.MainScreen.path) },
        onClickWhite = { navController.navigate(Route.SubscriptionsScreen.path) },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Box(modifier = Modifier.fillMaxSize()) {
        HeaderUserProfile(modifier = Modifier.align(Alignment.TopCenter))

        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
        ) {
            HeaderWithBackButton(
                text = "",
                colorText = MaterialTheme.colorScheme.onPrimary,
                tint = MaterialTheme.colorScheme.onPrimary,
                imageButton = R.drawable.ic_three_dots_22,
                onClickBack = { navController.navigate(Route.MainScreen.path) },
                onClickOther = { expandedMenu.value = true },
                actionsMenu = {
                    CommonActionsMenu(
                        expand = expandedMenu,
                        actions = listOf(R.string.block),
                        icons = listOf(R.drawable.ic_blocked_24),
                        onClicks = listOf { showDialog.value = true }
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Box(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.unknown_user_foto),
                    contentDescription = "User foto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .align(Alignment.Center)
                )
                SubscribersCard(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 13.dp)
                        .fillMaxWidth(0.3f),
                    text = stringResource(id = R.string.subscribers),
                    count = 150
                )
                SubscribersCard(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 13.dp)
                        .fillMaxWidth(0.3f),
                    text = stringResource(id = R.string.subscriptions),
                    count = 25
                )
            }

            LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "name",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                                contentDescription = "Star",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "rating",
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontWeight = FontWeight.W500),
                                color = MaterialTheme.colorScheme.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning_12),
                                contentDescription = "Warning",
                                modifier = Modifier.clickable { expandedHint.value = true }
                            )
                            DropDownHint(
                                expand = expandedHint,
                                text = stringResource(id = R.string.organizeGames)
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = "position, levelPlay",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                item {
                    Text(
                        text = "long text \nlong text \nlong text \nlong text \nlong text",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                        color = MaterialTheme.colorScheme.primary,
                        lineHeight = 20.sp,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        CommonProfileGreenCard(
                            titleText = stringResource(id = R.string.bestPlayer),
                            icon = R.drawable.ic_medal_24,
                            blackText = "10",
                            greyText = "",
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

                item {//будет только для организатора игр
                    Quality(
                        modifier = Modifier.padding(top = 16.dp),
                        quality = stringResource(id = R.string.goodMentor),//вопрос у менеджера
                        count = 15
                    )
                }

                item {
                    Text(
                        text = "players games",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 20.dp, bottom = 16.dp)
                    )
                }

                items(3) {
                    GameCard(place = "place", host = "name host", address = "address")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = MaterialTheme.colorScheme.primaryContainer),
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            val animatedContainerColor: Color by animateColorAsState(
                targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.tertiary,
                animationSpec = tween(500, 0, LinearEasing)
            )
            val animatedContentColor: Color by animateColorAsState(
                targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondaryContainer,
                animationSpec = tween(500, 0, LinearEasing)
            )
            val animatedContainerColor2: Color by animateColorAsState(
                targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.secondary,
                animationSpec = tween(500, 0, LinearEasing)
            )

            CommonButton(
                text = stringResource(id = if (buttonEnable.value) R.string.unsubscribe
                    else R.string.subscribe),
                containerColor = animatedContainerColor2,
                onClick = { buttonEnable.value = !buttonEnable.value },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 12.dp, end = 8.dp)
                    .fillMaxWidth(0.55f)
                    .align(Alignment.CenterStart)
            )
            CommonButton(
                text = stringResource(id = R.string.inviteProfile),
                containerColor = animatedContainerColor,
                contentColor = animatedContentColor,
                enable = buttonEnable.value,
                onClick = {/*navigate to calendar*/ },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, end = 12.dp)
                    .align(Alignment.CenterEnd)
                    .fillMaxWidth(0.4f)
            )
        }
    }
}

@Composable
private fun SubscribersCard(modifier: Modifier = Modifier, text: String, count: Int) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun Quality(modifier: Modifier = Modifier, quality: String, count: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.border(
            width = 1.dp,
            shape = RoundedCornerShape(68.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer)
    ) {
        Text(
            text = quality,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_green_rectangle_6),
            contentDescription = "Dot",
            tint = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}