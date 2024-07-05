package com.example.footballplayassistant.presentation.customviews.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.zIndex

@Composable
fun SelectionButtons(
    modifier: Modifier = Modifier,
    valueList: List<String>,
    selectedItemIndex: Int,
    onSelected: (Int) -> Unit
) {
    val animatedButtonColor: Color by animateColorAsState(
        targetValue = if (selectedItemIndex == 0) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.outlineVariant,
        animationSpec = tween(500, 0, LinearEasing)
    )
    val animatedBorderColor: Color by animateColorAsState(
        targetValue = if (selectedItemIndex == 0) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.onPrimaryContainer,
        animationSpec = tween(500, 0, LinearEasing)
    )
    val animatedButtonColor2: Color by animateColorAsState(
        targetValue = if (selectedItemIndex == 1) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.outlineVariant,
        animationSpec = tween(500, 0, LinearEasing)
    )
    val animatedBorderColor2: Color by animateColorAsState(
        targetValue = if (selectedItemIndex == 1) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.onPrimaryContainer,
        animationSpec = tween(500, 0, LinearEasing)
    )

    Box(modifier = modifier.fillMaxWidth()) {
        CurrentButton(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .align(Alignment.CenterStart)
                .zIndex(if (selectedItemIndex == 0) 1f else 0f),
            borderColor = animatedBorderColor,
            containerColor = animatedButtonColor,
            text = valueList[0],
            onSelected = { onSelected(0) }
        )
        CurrentButton(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .align(Alignment.CenterEnd)
                .zIndex(if (selectedItemIndex == 0) 0f else 1f),
            borderColor = animatedBorderColor2,
            containerColor = animatedButtonColor2,
            text = valueList[1],
            onSelected = { onSelected(1) }
        )
    }
}

@Composable
private fun CurrentButton(
    modifier: Modifier = Modifier,
    borderColor: Color,
    containerColor: Color,
    text: String,
    onSelected: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = containerColor, shape = RoundedCornerShape(80.dp))
            .border(1.dp, borderColor, RoundedCornerShape(80.dp))
            .clickable { onSelected() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}