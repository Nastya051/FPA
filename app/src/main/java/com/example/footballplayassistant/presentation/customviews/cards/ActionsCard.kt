package com.example.footballplayassistant.presentation.customviews.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun ActionsCard(
    iconTextList: List<Pair<Int, String>>,
    actionsList: List<() -> Unit>,
    cardColor: Color = MaterialTheme.colorScheme.primaryContainer,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        for (i in iconTextList.indices) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 15.5.dp)
                    .clickable { actionsList[i]() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 12.dp, bottom =
                    if(i==iconTextList.size - 1) 12.dp else 0.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = iconTextList[i].first),
                        contentDescription = "Start icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = iconTextList[i].second,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next_24),
                    contentDescription = "Arrow",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 12.dp, bottom =
                    if(i==iconTextList.size - 1) 12.dp else 0.dp)
                )
            }
            if (i != iconTextList.size - 1)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.5.dp)
                )
        }
    }
}