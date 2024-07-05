package com.example.footballplayassistant.presentation.ui.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.CommonSwitch
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.checkboxes.CheckBoxInventory
import com.example.footballplayassistant.presentation.customviews.checkboxes.CommonCheckBoxAgree
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.ButtonDropDownMenu
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.DropDownMenu
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.radiobuttons.RadioButtonGroup
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.screens.authentication.addStar
import com.example.footballplayassistant.presentation.ui.theme.spacing
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun CreateEventScreen() {
    val navController = LocalNavController.current!!

    val buttonEnable = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    val field = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val timeStart = remember { mutableStateOf("") }
    val timeEnd = remember { mutableStateOf("") }
    val count = remember { mutableStateOf("") }
    val inventory = remember { mutableStateOf(true) }
    val cost = remember { mutableStateOf("") }
    val agree = remember { mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.gameCreated),
        description = stringResource(id = R.string.youGetNotify),
        greenButton = stringResource(id = R.string.onGamePage),
        whiteButton = stringResource(id = R.string.inviteFriends),
        bottomButton = stringResource(id = R.string.copy),
        image = R.drawable.ic_check_92,
        onClickGreen = { navController.navigate(Route.MatchScreen.path) },
        onClickWhite = { navController.navigate(Route.InviteFriendsScreen.path) },
        onClickBottom = {/*copy invitation*/ navController.navigate(Route.MatchScreen.path) },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimary)) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.addGame),
            onClickBack = { navController.navigate(Route.MainScreen.path) },
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        Box(modifier = Modifier) {
            LazyColumn {
                item {
                    BoldText(
                        id = R.string.field,
                        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
                    )

                    DropDownMenu(
                        placeholder = "field",
                        values = listOf("Поле 1", "Поле 2"),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        onClick = { field.value = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                item {
                    CommonSwitch(
                        text = stringResource(id = R.string.closeGame), icon = true,
                        textIcon = stringResource(id = R.string.tooltipClose),
                        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    )
                }
                item {
                    CommonSwitch(
                        text = stringResource(id = R.string.iWill), icon = true,
                        textIcon = stringResource(id = R.string.tooltipPlay),
                        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    )
                }
                item {
                    BoldText(id = R.string.date, modifier = Modifier.padding(top = 24.dp))
                    val dates = mutableListOf<String>()
                    val now = OffsetDateTime.now()
                    for (day in 0..30) {
                        val nextDay = now.plusDays(day.toLong())
                        dates.add(
                            index = day,
                            element = "${if (nextDay.dayOfMonth > 9) nextDay.dayOfMonth
                                else "0${nextDay.dayOfMonth}"}." +
                                    "${if (nextDay.monthValue > 9) nextDay.monthValue
                                        else "0${nextDay.monthValue}"}." + "${nextDay.year}"
                        )
                    }
                    ButtonDropDownMenu(
                        placeholder = stringResource(id = R.string.date),
                        values = dates,
                        imStart = R.drawable.ic_calendar_22,
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        onClick = { date.value = it },
                        modifier = Modifier.padding(bottom = 10.dp, top = 12.dp, start = 16.dp, end = 16.dp)
                    )
                }
                item {
                    BoldText(
                        id = R.string.time,
                        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        val time = mutableListOf<String>()
                        for (i in 0..23) {
                            time.add(index = i * 2, element = if (i < 10) "0$i:00" else "$i:00")
                            time.add(index = i * 2 + 1, element = if (i < 10) "0$i:30" else "$i:30")
                        }
                        ButtonDropDownMenu(
                            placeholder = stringResource(id = R.string.start),
                            imTrail = R.drawable.ic_time_black_24,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(end = 4.dp),
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            values = time,
                            onClick = { timeStart.value = it },
                        )
                        ButtonDropDownMenu(
                            placeholder = stringResource(id = R.string.end),
                            imTrail = R.drawable.ic_time_black_24,
                            modifier = Modifier.padding(start = 4.dp),
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            values = time,
                            onClick = { timeEnd.value = it },
                        )
                    }
                }
                item {
                    BoldText(
                        addStar = false,
                        id = R.string.details,
                        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
                    )
                    CommonTextField(
                        placeholder = stringResource(id = R.string.title),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                    )
                    Box(
                        modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                    ) {
                        CommonTextField(
                            placeholder = stringResource(id = R.string.otherInfo),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            singleLine = false,
                            cornerRadius = 20.dp,
                            maxLength = 300,
                            modifier = Modifier.fillMaxHeight()
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_rezible_10),
                            contentDescription = "Rezible",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(
                                    end = MaterialTheme.spacing.small,
                                    bottom = MaterialTheme.spacing.small
                                )
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    ) {
                        BoldText(
                            id = R.string.countPlayers,
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .align(Alignment.CenterVertically)
                        )
                        val countPlayers = mutableListOf<String>()
                        for (i in 0..10)
                            countPlayers.add(index = i, element = "${i * 2 + 2}")
                        ButtonDropDownMenu(
                            placeholder = "",
                            imStart = R.drawable.ic_people_24,
                            values = countPlayers,
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            onClick = { count.value = it },
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
                item {
                    BoldText(addStar = false, id = R.string.sexPlayers)
                    RadioButtonGroup()
                }
                item {
                    BoldText(id = R.string.inventory, modifier = Modifier.padding(top = 24.dp))
                    CheckBoxInventory(onClick = {if(it.contains(true)) inventory.value = true})
                }
                item {
                    BoldText(
                        id = R.string.costEntry,
                        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cost),
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.W500),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth(0.53f)
                                .padding(start = 10.dp),
                        )

                        Text(
                            text = stringResource(id = R.string.commission),
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.W500),
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        val price = remember { mutableStateOf("0") }
                        CommonTextField(
                            placeholder = price.value,
                            imageTrail = R.drawable.ic_ruble_15,
                            keyBoard = KeyboardType.Number,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            onClick = { price.value = it
                                      cost.value = it},
                            modifier = Modifier.weight(0.5f),
                        )
                        CommonTextField(
                            placeholder = if (price.value.isNotEmpty()) "${(price.value).toInt() / 10}"
                            else "0",
                            imageTrail = R.drawable.ic_ruble_15,
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            tintIcon = MaterialTheme.colorScheme.onSecondaryContainer,
                            readOnly = true,
                            modifier = Modifier.weight(0.5f),
                        )
                    }
                }
                item {
                    CommonCheckBoxAgree(onClick = { agree.value = it })
                }
                item {
                    LaunchedEffect(field.value, date.value, timeStart.value, timeEnd.value,
                        count.value, inventory.value, cost.value, agree.value){
                        buttonEnable.value = field.value.isNotEmpty() && date.value.isNotEmpty() &&
                                timeStart.value.isNotEmpty() && timeEnd.value.isNotEmpty() &&
                                count.value.isNotEmpty() && inventory.value &&
                                cost.value.isNotEmpty() && agree.value
                    }
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
                    CommonButton(
                        text = stringResource(id = R.string.addEvent),
                        style = MaterialTheme.typography.bodyLarge,
                        containerColor = animatedContainerColor,
                        contentColor = animatedContentColor,
                        enable = buttonEnable.value,
                        onClick = { showDialog.value = true },
                        modifier = Modifier.padding(
                            top = 16.dp,
                            bottom = 20.dp,
                            start = 16.dp,
                            end = 16.dp)
                    )
                    ClickableText(
                        onClick = { navController.navigate(Route.MainScreen.path) },
                        text = AnnotatedString(stringResource(id = R.string.cancel)),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BoldText(
    id: Int,
    addStar: Boolean = true,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    if (addStar)
        Text(
            text = addStar(
                id = id,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily(Font(R.font.inter_semibold)),
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier.padding(horizontal = MaterialTheme.spacing.horizontal)
        )
    else
        Text(
            text = stringResource(id = id),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(horizontal = MaterialTheme.spacing.horizontal)
        )
}
