package com.example.footballplayassistant.presentation.customviews.dropdownmenus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CommonActionsMenu(
    expand: MutableState<Boolean>,
    actions: List<Int>,
    icons: List<Int> = listOf(),
    onClicks: List<() -> Unit>,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(20.dp))) {
        DropdownMenu(
            expanded = expand.value,
            onDismissRequest = { expand.value = false },
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(20.dp))
                .widthIn(min = 290.dp)
        ) {
            for (i in actions.indices) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = actions[i]),
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.W400,
                                color = color),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    trailingIcon = {
                        if (icons.isNotEmpty()) Icon(
                            imageVector = ImageVector.vectorResource(id = icons[i]),
                            contentDescription = "Icon trail",
                            tint = color
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp),
                    onClick = {
                        onClicks[i].invoke()
                        expand.value = false
                    }
                )
                if (actions.size > 1 && i != actions.size - 1) {
                    HorizontalDivider(
                        thickness = 1.dp, color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
    }
}