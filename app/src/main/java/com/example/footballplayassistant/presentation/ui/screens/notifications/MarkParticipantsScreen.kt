package com.example.footballplayassistant.presentation.ui.screens.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.screens.search_tab.Player
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun MarkParticipantsScreen() {
    val players = listOf(
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Егор", "Дружин", R.drawable.player_example),
        Player("Серявгей", "Сергеев", R.drawable.player_example_1),
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Игорь", "Султанов", R.drawable.loadexample),
        Player("Игорь", "Султанов", R.drawable.loadexample),
    )
    val date = "23.05.2023"
    val stateList = remember { List(players.size) { false }.toMutableStateList() }
    var markAll by remember { mutableStateOf(false) }
    Column {
        HeaderWithBackButton(text = stringResource(R.string.markParticipantsDateHeader, date),
            modifier = Modifier.padding(MaterialTheme.spacing.horizontal))
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.horizontal
                )) {
            Icon(painterResource(R.drawable.ic_people_24), contentDescription = stringResource(R.string.markAllParticipantsDescription),
                tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier
                    .size(50.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(Color.Black)
                    .padding(MaterialTheme.spacing.small)
                )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    stringResource(R.string.markEveryoneCheckbox),
                    modifier = Modifier.weight(1f, fill = false),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.W700
                    ))
                Checkbox(
                    checked = markAll,
                    onCheckedChange = {
                        markAll = it
                        for (i in players.indices) {
                            stateList[i] = it
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.secondary,
                        uncheckedColor = MaterialTheme.colorScheme.secondary,
                    ),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
        LazyColumn {
            items(players.size) { i ->
                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.horizontal,
                            vertical = MaterialTheme.spacing.small
                        )) {
                    PlayerWithCheckboxTagCard(tag = "@tag",
                        name = "${players[i].name} ${players[i].surname}",
                        image = players[i].photo,
                        modifier = Modifier.weight(1f, fill = false))
                    Checkbox(
                        checked = stateList[i],
                        onCheckedChange = { it ->
                            stateList[i] = it
                            markAll = stateList.count { it } == players.size
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.secondary,
                            uncheckedColor = MaterialTheme.colorScheme.secondary,
                        ),
                        modifier = Modifier.size(24.dp)
                    )
                }
                HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            CommonButton(stringResource(R.string.finishMarkingButtonText), enable = stateList.count { it } != 0)
            Text(
                stringResource(R.string.cancelMarkingButtonText), style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.W600
            ))
        }
    }
}


@Composable
fun PlayerWithCheckboxTagCard(image : Int, name : String, tag : String,
                              modifier : Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
        Image(
            painter = painterResource(id = image), contentDescription = stringResource(R.string.markPlayerAvatar),
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = tag,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}


