package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.rows.BottomRowDateTimeMoney
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
@Preview
fun PaymentScreen() {
    val navController = LocalNavController.current!!
    val showDialog = remember { mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.payOK),
        description = stringResource(id = R.string.darkdontForget),
        greenButton = stringResource(id = R.string.onGamePage),
        whiteButton = stringResource(id = R.string.returnMain),
        bottomButton = stringResource(id = R.string.inviteFriendsAlso),
        image = R.drawable.ic_check_92,
        onClickGreen = { navController.navigate(Route.MatchScreen.path) },
        onClickWhite = { navController.navigate(Route.MainScreen.path) },
        onClickBottom = { navController.navigate(Route.InviteFriendsScreen.path) },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Scaffold(containerColor = MaterialTheme.colorScheme.primaryContainer,
        bottomBar = {
            Column {
                CommonButton(
                    text = stringResource(id = R.string.pay),
                    onClick = { showDialog.value = true },
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                BottomWarning()
                //не хватает денег
//                    Text(
//                        text = stringResource(id = R.string.paymentFail),
//                        textAlign = TextAlign.Center,
//                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
//                        color = MaterialTheme.colorScheme.errorContainer,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp, bottom = 24.dp)
//                    )
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(it)
        ) {
            HeaderWithBackButton(
                text = stringResource(id = R.string.payment),
                onClickBack = { navController.navigate(Route.MatchScreen.path) },
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            )

            LazyColumn {
                item { BalanceCard(price = "1000") }
                item {
                    EventCard(
                        place = "place",
                        date = "01.07.2024",
                        price = "1000₽/чел",
                        time = "12:00"
                    )
                }
                item {
                    Text(
                        text = stringResource(id = R.string.detailsOperation),
                        style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.W500,
                                fontFamily = FontFamily(Font(R.font.inter_medium))
                            ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 12.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.partInMatch),
                            style = MaterialTheme.typography.titleLarge
                                .copy(fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(12.dp)
                        )
                        Text(
                            text = "800₽",
                            style = MaterialTheme.typography.labelLarge
                                .copy(fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(12.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.total),
                            style = MaterialTheme.typography.displayMedium
                                .copy(fontWeight = FontWeight.W500),
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = "800₽",
                            style = MaterialTheme.typography.displayLarge
                                .copy(fontWeight = FontWeight.W600),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EventCard(place: String, date: String, time: String, price: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 20.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = place,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(12.dp)
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        BottomRowDateTimeMoney(
            date = date, time = time, price = price,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
private fun BalanceCard(price: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.balance),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = price,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = stringResource(id = R.string.currency),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 8.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.replenish),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}

@Composable
fun BottomWarning() {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
        ) {
            append(stringResource(id = R.string.cancelWithoutLoss))
        }
        appendInlineContent(myId, "[icon]")
    }

    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = 20.sp,
                    height = 20.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_question_14),
                    contentDescription = "Question",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        )
    )

    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        inlineContent = inlineContent
    )
}