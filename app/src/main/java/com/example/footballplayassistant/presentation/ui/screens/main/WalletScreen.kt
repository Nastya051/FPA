package com.example.footballplayassistant.presentation.ui.screens.main


import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.enums.FilterCurrentArchive
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun WalletScreen() {
    val navController = LocalNavController.current!!
    val countEvents = 5//пока просто для проверки
    val host = true//пока просто для проверки
    val showDialog = remember { mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.youDidntRate),
        description = stringResource(id = R.string.youShouldRate),
        greenButton = stringResource(id = R.string.goToAchive),
        whiteButton = stringResource(id = R.string.returnToWallet),
        image = R.drawable.ic_warning_93,
        onClickGreen = { navController.navigate(Route.MyGamesScreen.withArgs(
            FilterCurrentArchive.Archive.ordinal.toString())) },
        onClickWhite = { showDialog.value = false },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Scaffold(
        topBar = {
            HeaderWithBackButton(
                text = stringResource(id = R.string.myWallet),
                onClickBack = { navController.navigate(Route.MainScreen.path) },
                modifier = Modifier.padding(top = 12.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            )
        },
        bottomBar = {
            CommonButton(
                text = stringResource(id = R.string.replenishBalance),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp)) },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy((-50).dp)
                ) {
                    CurrentCard(
                        cardColor = MaterialTheme.colorScheme.onPrimary,
                        titleTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        moneyColor = MaterialTheme.colorScheme.primary,
                        actionColor = MaterialTheme.colorScheme.primary,
                        titleText = stringResource(id = R.string.balanceWallet),
                        actionText = stringResource(id = R.string.replenish),
                        money = 1500,
                        onClick = {/*переход по API на платежную систему*/ },
                        modifier = Modifier.zIndex(1f)
                    )

                    CurrentCard(
                        cardColor = MaterialTheme.colorScheme.primary,
                        titleTextColor = MaterialTheme.colorScheme.onPrimary,
                        moneyColor = MaterialTheme.colorScheme.onPrimary,
                        actionColor = MaterialTheme.colorScheme.onPrimary,
                        titleText = stringResource(id = R.string.summaWithdrawal),
                        actionText = stringResource(id = R.string.bringOut),
                        money = 1500,
                        onClick = {/*переход по API на платежную систему, либо если игра не оценена, то диалог (только для хоста)*/
                            if (host)
                                showDialog.value = true
                        },
                        topPadding = 50.dp,
                        modifier = Modifier
                            .zIndex(0.7f)
                            .padding(top = 20.dp)
                    )

                    CurrentCard(
                        cardColor = MaterialTheme.colorScheme.outlineVariant,
                        titleTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        moneyColor = MaterialTheme.colorScheme.primary,
                        actionColor = MaterialTheme.colorScheme.primary,
                        borderColor = MaterialTheme.colorScheme.primary,
                        borderTextColor = if (countEvents > 0) MaterialTheme.colorScheme.tertiaryContainer
                        else MaterialTheme.colorScheme.outlineVariant,
                        titleText = stringResource(id = R.string.hold),
                        actionText = if (countEvents > 0) pluralStringResource(
                            id = R.plurals.events,
                            count = countEvents,
                            countEvents)
                        else "",
                        money = 1500,
                        onClick = {
                            if (countEvents > 0) navController.navigate(
                                Route.MyGamesScreen.withArgs(
                                    FilterCurrentArchive.Current.ordinal.toString()))
                        },
                        topPadding = 50.dp,
                        modifier = Modifier
                            .zIndex(0.5f)
                            .padding(top = 20.dp)
                    )
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.historyOperations),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
                )
            }
            items(3) {
                OperationCard(operationsList = listOf(1, 1))
            }
        }
    }
}

@Composable
private fun CurrentCard(
    cardColor: Color,
    titleTextColor: Color,
    moneyColor: Color,
    actionColor: Color,
    titleText: String,
    money: Int,
    actionText: String = "",
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    borderTextColor: Color = MaterialTheme.colorScheme.outlineVariant,
    topPadding: Dp = 20.dp,
    onClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, shape = RoundedCornerShape(12.dp), color = borderColor)
            .clickable { onClick() }
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = titleTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = topPadding, start = 12.dp, end = 12.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "$money",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W600),
                    color = moneyColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.currency),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400),
                    color = moneyColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Text(
                text = actionText,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                color = actionColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(43.dp),
                        color = borderTextColor)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun OperationCard(operationsList: List<Int>) {
    Column(
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "date",
            style = MaterialTheme.typography.labelLarge.copy(
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                for (i in operationsList.indices) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp, start = 24.dp, end = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "visa classic",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = "+250p",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.W600),
                            color = MaterialTheme.colorScheme.secondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.replenishment),
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight.W400),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )

                    if (operationsList.size > 1 && i != operationsList.size - 1)
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 16.dp)
                        )
                }
            }
        }
    }
}