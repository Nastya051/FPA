package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.CommonSwitch
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.checkboxes.CheckBoxInventory
import com.example.footballplayassistant.presentation.customviews.checkboxes.CommonCheckBoxAgree
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.radiobuttons.RadioButtonGroup
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.screens.authentication.addStar
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun EditGameScreen() {
    val navController = LocalNavController.current!!

    val flag = remember { mutableStateOf(true) }
    val showDialogQuestion = remember { mutableStateOf(false) }
    val showDialogEnd = remember { mutableStateOf(false) }

        DialogScreen(
            header = stringResource(id = R.string.dismissMatchQuestion),
            description = stringResource(id = R.string.itCanBeNegative),
            whiteButton = stringResource(id = R.string.dismissMatch),
            bottomButton = stringResource(id = R.string.cancel),
            arrowsIcon = false,
            image = R.drawable.ic_warning_93,
            onClickWhite = { showDialogEnd.value = true },
            onClickBottom = { showDialogQuestion.value = false },
            onDismissRequest = { showDialogQuestion.value = false },
            showDialog = showDialogQuestion.value
        )

        DialogScreen(
            header = stringResource(id = R.string.matchDismissed),
            description = stringResource(id = R.string.returnAllMoney),
            whiteButton = stringResource(id = R.string.goToMain),
            onClickWhite = { navController.navigate(Route.MainScreen.path) },
            onDismissRequest = { showDialogEnd.value = false },
            showDialog = showDialogEnd.value
        )

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimary)) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.editGame),
            onClickBack = { navController.navigate(Route.MatchScreen.path) },
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)
        )

        LazyColumn {
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
                Text(
                    text = stringResource(id = R.string.field),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 24.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
                )
                DisabledField(
                    text = "sfsfs", startIcon = 0, endIcon = R.drawable.ic_arrow_next_24,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.date),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                )
                DisabledField(
                    text = "sfsfs",
                    startIcon = R.drawable.ic_calendar_gray_24,
                    endIcon = R.drawable.ic_arrow_menu_18_10,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.time),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                ) {
                    DisabledField(
                        text = "sfsfs", startIcon = 0, endIcon = R.drawable.ic_time_24,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(end = 12.dp)
                    )
                    DisabledField(text = "sfsfs", startIcon = 0, endIcon = R.drawable.ic_time_24)
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.details),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                )
                Box(modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)) {
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
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = addStar(
                            id = R.string.countPlayers,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
                            color = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                    DisabledField(
                        text = "sfsfs",
                        startIcon = R.drawable.ic_people_24,
                        endIcon = R.drawable.ic_arrow_menu_18_10
                    )
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.sexPlayers),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                )
                RadioButtonGroup(enabled = false)
            }
            item {
                Text(
                    text = stringResource(id = R.string.playersWithout),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                )
                Button(
                    onClick = { navController.navigate(Route.ManagingParticipantsScreen.path) },
                    colors = ButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.manageParticipants),
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.W400),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next_24),
                            contentDescription = "Arrow"
                        )
                    }

                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.inventory),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                )
                CheckBoxInventory()
            }
            item {
                Text(
                    text = stringResource(id = R.string.costOfEntry),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 24.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.horizontal)
                        .padding(bottom = MaterialTheme.spacing.small),
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
                        .padding(horizontal = MaterialTheme.spacing.horizontal)
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    val price = 1000
                    CommonTextField(
                        placeholder = price.toString(),
                        imageTrail = R.drawable.ic_ruble_15,
                        keyBoard = KeyboardType.Number,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        readOnly = true,
                        modifier = Modifier.weight(0.5f),
                    )
                    CommonTextField(
                        placeholder = "${price / 10}",
                        imageTrail = R.drawable.ic_ruble_15,
                        color = MaterialTheme.colorScheme.scrim,
                        tintIcon = MaterialTheme.colorScheme.onSecondaryContainer,
                        readOnly = true,
                        modifier = Modifier.weight(0.5f),
                    )
                }
            }
            item {
                CommonCheckBoxAgree()
            }
            item {
                ClickableText(
                    onClick = { showDialogQuestion.value = true },
                    text = AnnotatedString(stringResource(id = R.string.dismissMatch)),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, top = 32.dp)
                )
            }
            item {
                if (flag.value) {
                    CommonButton(
                        text = stringResource(id = R.string.makeChanges),
                        onClick = { /*TODO*/ },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.horizontal)
                            .padding(top = MaterialTheme.spacing.medium, bottom = 20.dp)
                    )
                } else {
                    CommonButton(
                        text = stringResource(id = R.string.makeChanges),
                        style = MaterialTheme.typography.bodyLarge,
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.horizontal)
                            .padding(top = MaterialTheme.spacing.medium, bottom = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DisabledField(
    text: String,
    startIcon: Int,
    endIcon: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(60.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            if (startIcon != 0) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = startIcon),
                    contentDescription = "Start icon",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.tertiary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = if (startIcon != 0) 8.dp else 16.dp)
            )
        }


        if (endIcon != 0) {
            Icon(
                imageVector = ImageVector.vectorResource(id = endIcon),
                contentDescription = "End icon",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}