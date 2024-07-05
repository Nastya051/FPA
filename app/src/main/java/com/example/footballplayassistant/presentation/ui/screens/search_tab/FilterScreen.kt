package com.example.footballplayassistant.presentation.ui.screens.search_tab

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.CommonSwitch
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.theme.spacing


@Composable
@Preview
fun FilterScreen() {
    val navController = LocalNavController.current!!
    Column (modifier = Modifier
        .background(color = MaterialTheme.colorScheme.primaryContainer)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        Column(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium, top = MaterialTheme.spacing.medium)
        ) {
            HeaderWithBackButton(text = stringResource(id = R.string.filterHeader),
                onClickBack = { navController.navigate(Route.SearchScreen.path) })
            CommonSwitch(text = stringResource(id = R.string.filterByFavorites))
            CommonSwitch(text = stringResource(id = R.string.filterByDistance))
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
            FilterRangeSlider(stringResource(id = R.string.awayFromUser), activeRangeStart = 0f, activeRangeEnd = 50f, needTwoThumbs = true)
            FilterRangeSlider(stringResource(id = R.string.amountOfPlayers), activeRangeStart = 12f, activeRangeEnd = 48f, needTwoThumbs = true)

            ToggleButton(
                stringResource(R.string.typesOfArena),
                stringArrayResource(id = R.array.typesOfArenaArray)
            )
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.large))
            ToggleButton(
                stringResource(id = R.string.coveringType),
                stringArrayResource(id = R.array.coveringTypeArray)
            )
        }
        Spacer(modifier = Modifier.weight(1f, fill = false))
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.tertiaryContainer)
        FilterBottomBar {
            navController.navigate(Route.SearchScreen.path)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterBottomBar(onClick : () -> Unit) {
    FlowRow(modifier = Modifier
        .height(IntrinsicSize.Min)
        .fillMaxWidth()
        .padding(vertical = MaterialTheme.spacing.medium, horizontal = MaterialTheme.spacing.medium), horizontalArrangement = Arrangement.SpaceEvenly) {

        FilterButton(stringResource(id = R.string.dropFilters),
            MaterialTheme.spacing.large,
            MaterialTheme.colorScheme.outlineVariant,
            MaterialTheme.colorScheme.onPrimaryContainer,
            onClick)
        FilterButton(stringResource(id = R.string.applyFilters),
            MaterialTheme.spacing.extraLarge,
            MaterialTheme.colorScheme.onPrimaryContainer,
            MaterialTheme.colorScheme.onPrimary,
            onClick)
    }
}

@Composable
fun FilterButton(text : String, horizontalPadding : Dp, containerColor : Color, contentColor: Color, onClick : () -> Unit) {
    Button(onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = horizontalPadding,
            vertical = MaterialTheme.spacing.medium
        ),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor,
            contentColor = contentColor)) {
        Text(text, style = MaterialTheme.typography.bodySmall)
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ToggleButton(title: String = "", items: Array<String>, selectAll : Boolean = true) {
    val selectedIndex = remember {
        if (selectAll) {
        items.indices.toList().toMutableStateList()
    } else {
        mutableStateListOf()
    }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        if (title.isNotEmpty()) {
            Text(
                title,
                style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
        FlowRow(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            items.forEachIndexed { index, item ->
                OutlinedButton(
                    contentPadding = PaddingValues(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.small
                    ),
                    onClick = { selectedIndex.swap(toggleLogic(selectedIndex, index, selectAll)) },
                    shape = RoundedCornerShape(8.dp),
                    border = if (index in selectedIndex) {
                        BorderStroke(1.dp, color =  MaterialTheme.colorScheme.secondary)
                    } else {
                        BorderStroke(1.dp, color =  MaterialTheme.colorScheme.tertiaryContainer)
                    },
                    colors = if (index in selectedIndex) {
                        ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.outlineVariant)
                    }
                ) {
                    Text(text = item, modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),  color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
    }
}

private fun <T> SnapshotStateList<T>.swap(list: List<T>) {
    this.clear()
    this.addAll(list)
}

fun toggleLogic(list : List<Int>, element : Int, selectAll: Boolean) : List<Int> {
    val newList = list.toMutableList()
    if (newList.contains(element) && (newList.size > 1 || !selectAll)) {
        newList.remove(element)
    } else if (!newList.contains(element)) {
        newList.add(element)
    }
    return newList.toList()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterRangeSlider(text: String, style : TextStyle = MaterialTheme.typography.displayMedium
    .copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                      activeRangeStart: Float, activeRangeEnd: Float,
                      needTwoThumbs: Boolean = false) {
    var sliderPosition by remember{ mutableFloatStateOf(activeRangeStart) }
    var rangeSliderPosition by remember { mutableStateOf(activeRangeStart..activeRangeEnd) }
    Column(modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                maxLines = 2,
                text = text,
                modifier = Modifier.weight(1f, fill = false),
                style = style,
                overflow = TextOverflow.Ellipsis

            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.extraSmall
                    )
            ) {
                if (needTwoThumbs) {
                    Text(
                        "${rangeSliderPosition.start.toInt()}-${rangeSliderPosition.endInclusive.toInt()}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
                    )
                } else {
                    Text(
                        "${sliderPosition.toInt()}",
                        style = MaterialTheme.typography.bodyMedium.copy(color =
                            if (sliderPosition == 0f) {
                                MaterialTheme.colorScheme.onSecondaryContainer
                            } else {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            }
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))

        if (needTwoThumbs) {
            RangeSlider(
                value = rangeSliderPosition,
                onValueChange = { rangeSliderPosition = it },
                valueRange = activeRangeStart..activeRangeEnd,
                startThumb = { ThumbKnob() },
                endThumb = { ThumbKnob() },
                track = { SliderDefaults.Track(
                    modifier = Modifier.height(2.dp),
                    colors = sliderTrackColors(),
                    rangeSliderState = it
                    )
                }
            )
        } else {
            Slider(
                value = sliderPosition,
                valueRange = activeRangeStart..activeRangeEnd,
                steps = (activeRangeEnd - activeRangeStart).toInt(),
                onValueChange = { sliderPosition = it },
                thumb = { ThumbKnob() },
                track = { SliderDefaults.Track(
                    sliderState = it,
                    modifier = Modifier.height(2.dp),
                    colors = sliderTrackColors(),
                    )
                }
            )

        }
    }
}

@Composable
fun ThumbKnob() {
    Image(
        painterResource(R.drawable.knob),
        contentDescription = stringResource(id = R.string.knobIconDescription)
    )
}


@Composable
fun sliderTrackColors(): SliderColors {
    return SliderDefaults.colors(
        activeTrackColor = MaterialTheme.colorScheme.secondary,
        inactiveTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
        activeTickColor = MaterialTheme.colorScheme.outlineVariant,
        inactiveTickColor = MaterialTheme.colorScheme.outlineVariant
    )
}