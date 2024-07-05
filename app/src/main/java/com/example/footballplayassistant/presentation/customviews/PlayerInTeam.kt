package com.example.footballplayassistant.presentation.customviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun PlayerInTeam(
    userTag: String,
    position: String,
    foto: Int,
    isBorder: Boolean = false,
    deleteIcon: Boolean = false,
    onDelete: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (deleteIcon)
            Box(modifier = Modifier.sizeIn(minWidth = 55.dp, minHeight = 55.dp)) {
                Image(
                    painter = painterResource(id = foto), contentDescription = "User foto",
                    modifier = Modifier.border(
                        width = if (isBorder) 1.dp else 0.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(12.dp))
                        .align(Alignment.Center)
                )
                IconButton(
                    onClick = { onDelete() },
                    colors = IconButtonColors(containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        else
            Image(
                painter = painterResource(id = foto), contentDescription = "User foto",
                modifier = Modifier.border(
                    width = if (isBorder) 1.dp else 0.dp,
                    color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(12.dp)
                )
            )
        Text(
            text = userTag,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        Text(
            text = position,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.tertiaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}