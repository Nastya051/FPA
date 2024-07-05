package com.example.footballplayassistant.presentation.ui.screens.calendar_tab

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.cards.GameCard
import com.example.footballplayassistant.presentation.ui.screens.search_tab.NoResultsScreen
import com.example.footballplayassistant.presentation.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun CalendarScreen() {
    val selectedDate = LocalDate.now()
    val startDate = selectedDate.minusDays(3)
    val dayList = mutableListOf<LocalDate>()
    for (i in 0..selectedDate.month.maxLength() + 4) {
        dayList.add(startDate.plusDays(i.toLong()))
    }
    var chosenIndex by remember { mutableIntStateOf(3) }

    Column (modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primaryContainer)) {
        CalendarHeader(modifier = Modifier.padding(MaterialTheme.spacing.medium))
        Box(contentAlignment  = Alignment.CenterStart) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .wrapContentWidth(
                        align = Alignment.Start,
                        unbounded = true
                    )
                    .width(600.dp)
                    .height(60.dp)
                    .border
                        (
                        1.dp,
                        MaterialTheme.colorScheme.onPrimaryContainer,
                        RoundedCornerShape(50.dp)
                    )

            ) {
            }
            CalendarPager(dayList, chosenDay = { newValue -> chosenIndex = if (
                newValue == -1) { chosenIndex } else { newValue }})

        }
        val cardColor = MaterialTheme.colorScheme.onPrimary
        val filtersOn = false // Заглушка
        val found = true // Заглушка
        if (found) {
            LazyColumn(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                item {
                    GameCard(host = "", place = "Арена Новый Футбол поле  Крылатское",
                        cardColor = cardColor)
                    GameCard(host = "Игорь Султанов", place = "Арена Новый Футбол поле  Крылатское",
                        cardColor = cardColor) // Заглушка

                }
            }
        } else {
            NoResultsScreen(
                title = stringResource(R.string.calendarGameNotFoundTitle),
                description =
                if (filtersOn) {
                    stringResource(R.string.calendarFilterNotFoundDescription)
                } else {
                    stringResource(R.string.noFiltersCalendarNotFoundDescription)
                },
                buttonText = stringResource(R.string.createGameButtonText)
            ) {

            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarPager(dayList : MutableList<LocalDate>, chosenDay : (Int) -> Unit) {
    val state = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val paddingElementSize = ((configuration.screenWidthDp - 300) / 8).dp
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyRow(
            state = state, modifier = Modifier.padding(start = MaterialTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(paddingElementSize)
        ) {
            items(dayList.size) { index ->
                chosenDay(horizontalCalendarItem(
                    index = index,
                    date = dayList[index],
                    state = state,
                    hasEvents = !(index < 3 || index > dayList.size - 5))
                )
            }
        }
    }
}

fun LazyListState.animateScrollAndCentralizeItem(index: Int, scope: CoroutineScope) {
    val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    scope.launch {
        if (itemInfo != null) {
            val center = this@animateScrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2.2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            this@animateScrollAndCentralizeItem.animateScrollBy((childCenter - center).toFloat())
        } else {
            this@animateScrollAndCentralizeItem.animateScrollToItem(index)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun horizontalCalendarItem(index : Int,
                           date: LocalDate,
                           state : LazyListState, hasEvents : Boolean = false) : Int {

    val greenThumbColor = MaterialTheme.colorScheme.secondary
    val boxColor by remember {
        derivedStateOf {

            val layoutInfo = state.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            val itemInfo = visibleItemsInfo.firstOrNull { it.index == index}
            itemInfo?.let {
                if (visibleItemsInfo.indexOf(itemInfo) == 3)
                    return@derivedStateOf greenThumbColor
            }
            Color.Transparent
        }
    }
    val animatedDayColor: Color by animateColorAsState(
        targetValue = if (hasEvents) MaterialTheme.colorScheme.onPrimaryContainer
        else MaterialTheme.colorScheme.tertiaryContainer,
        animationSpec = tween(500, 0, LinearEasing)
    )
    val animatedWeekDayColor: Color by animateColorAsState(
        targetValue = if (hasEvents) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.tertiaryContainer,
        animationSpec = tween(500, 0, LinearEasing)
    )
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.clickable(
        onClick = {
            coroutineScope.launch {
                state.animateScrollAndCentralizeItem(index , this)
            }
        },

    ),
        contentAlignment = Alignment.Center) {
        val dayNumber = date.dayOfMonth.toString()
        Column(modifier = Modifier
            .padding(horizontal = 0.dp)
            .width(50.dp)
            .then(
                if (boxColor != Color.Transparent) {
                    Modifier
                        .height(76.dp)
                        .clip(
                            RoundedCornerShape(94.dp)
                        )
                        .background(boxColor)
                } else {
                    Modifier
                }
            ), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = MaterialTheme.typography.displaySmall.copy(
                    color = animatedWeekDayColor,
                    fontWeight = FontWeight.W600,
                )
            )
            Text(text =
            if (dayNumber.length == 1) {
                "0$dayNumber" 
            } else {
                dayNumber
                   },
                style = MaterialTheme.typography.displayMedium.copy(
                    color = animatedDayColor,
                    fontWeight = FontWeight.W600
                ))
        }
    }
    return if (boxColor == Color.Transparent) { -1 } else { index }
}

@Composable
fun CalendarHeader(modifier: Modifier) {
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        OutlinedIconButton(onClick = {}, modifier = Modifier.size(42.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer)) {
            Icon(painter = painterResource(id = R.drawable.ic_icons_24),
                contentDescription = stringResource(R.string.filterEventsCalendarIconDescription),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(21.dp))
        }
        Text(
            stringResource(R.string.calendarHeaderText), style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.W600
        ))

        OutlinedIconButton(onClick = {}, modifier = Modifier.size(42.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer)
        ) {
            Icon(painterResource(id = R.drawable.ic_plus_24),
                contentDescription = stringResource(R.string.addGameToCalendarIconDescription),
                modifier = Modifier.size(21.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer)

        }
    }
}