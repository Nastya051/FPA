package com.example.footballplayassistant.presentation.ui.screens.notifications

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.screens.search_tab.Event
import com.example.footballplayassistant.presentation.ui.screens.search_tab.NecessaryTextField
import com.example.footballplayassistant.presentation.ui.screens.search_tab.PhotoGrid
import com.example.footballplayassistant.presentation.ui.screens.search_tab.Player
import com.example.footballplayassistant.presentation.ui.screens.search_tab.ToggleButton
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
@Preview
fun EvaluateEventScreen() {
    val bestPlayer by remember { mutableIntStateOf(-1) }
    val event = Event(
        "Арена Новый Футбол поле  Крылатское",
        listOf(
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Егор", "Дружин", R.drawable.player_example),
            Player("Серявгей", "Сергеев", R.drawable.player_example_1),
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Игорь", "Султанов", R.drawable.loadexample),
        ),
        "27.07.2023",
        "10:00",
        500
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(MaterialTheme.spacing.medium)
            .verticalScroll(rememberScrollState())

    ) {
        HeaderWithBackButton(text = stringResource(R.string.evaluateEventHeader))
        Text(
            stringResource(R.string.evaluateEventDescription),
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
        ) {
            Row(
                Modifier.padding(MaterialTheme.spacing.extraSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                if (bestPlayer == -1) {
                    ChooseBestPlayer(event)
                } else {
                    BestPlayerChosen(event.participants[bestPlayer])
                }
            }
        }

        EvaluationCard(
            stringResource(R.string.rateHostTitle),
            arrayOf(
                stringArrayResource(R.array.terribleHostTags),
                stringArrayResource(R.array.badHostTags),
                stringArrayResource(R.array.okayHostTags),
                stringArrayResource(R.array.goodHostTags),
                stringArrayResource(R.array.excellentHostTags),
            )

        )
        EvaluationCard(
            stringResource(R.string.evaluateField),
            arrayOf(
                stringArrayResource(R.array.terribleFieldTags),
                stringArrayResource(R.array.badFieldTags),
                stringArrayResource(R.array.okayFieldTags),
                stringArrayResource(R.array.goodFieldTags),
                stringArrayResource(R.array.excellentFieldTags),
            )
        )
        Text(
            stringResource(R.string.leaveCommentAboutField),
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(
                    bottom = MaterialTheme.spacing.extraSmall,
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small
                )
        )
        NecessaryTextField(
            label = stringResource(R.string.commentLabelField),
            modifier = Modifier.defaultMinSize(minHeight = MaterialTheme.spacing.extraLarge),
            isSingleLine = false,
            shape = RoundedCornerShape(20.dp),
            toCountWords = 300,
            removeLabelAbove = true
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium)
        ) {
            CommonButton(stringResource(R.string.sendFeedbackButtonText))
            Text(
                stringResource(R.string.skipButtonText), style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}

@Composable
fun BestPlayerChosen(player: Player) {
    Image(painterResource(id = player.photo), contentDescription = stringResource(R.string.bestPlayerAvatarDescription),
        modifier = Modifier
            .size(66.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp, color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(12.dp)
            ), contentScale = ContentScale.Crop)
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f, fill = false)) {
            Text(stringResource(R.string.bestPlayerTitle))
            Text(player.name)
        }
        Icon(
            painterResource(id = R.drawable.ic_arrow_next_24),
            contentDescription = stringResource(R.string.eventButtonIconDescription),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChooseBestPlayer(event: Event) {
    PhotoGrid(event = event, addBorder = true)
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(R.string.chooseBestPlayer),
            modifier = Modifier.weight(1f, fill = false),
            style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Icon(
            painterResource(id = R.drawable.ic_arrow_next_24),
            contentDescription = stringResource(R.string.eventButtonIconDescription),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun EvaluationCard(title: String, items: Array<Array<String>>) {
    var rating by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(vertical = MaterialTheme.spacing.medium)
    ) {
        Text(
            title, style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        AnimatedVisibility(visible = rating > 0,
            enter = expandVertically(),
            exit = shrinkVertically()) {
            Text(
                when (rating) {
                    5 -> stringResource(R.string.excellentRating)
                    4 -> stringResource(R.string.goodRating)
                    3 -> stringResource(R.string.okayRating)
                    2 -> stringResource(R.string.badRating)
                    1 -> stringResource(R.string.terribleRating)
                    else -> ""
                },
                style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
        Row {
            repeat(rating) {
                IconButton(onClick = { rating = it + 1 }) {
                    Icon(
                        painterResource(id = R.drawable.ic_star), contentDescription = stringResource(R.string.filledRatingStarIconDescription),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            repeat(5 - rating) {
                IconButton(onClick = { rating = (rating + (it + 1)) }) {
                    Icon(
                        painterResource(id = R.drawable.ic_star), contentDescription = stringResource(R.string.blankRatingStarIconDescription),
                        tint = MaterialTheme.colorScheme.tertiaryContainer
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = rating > 0,
        enter = expandVertically(),
        exit = shrinkVertically()) {
        ToggleButton(items = items[rating - 1], selectAll = false)
    }

}

