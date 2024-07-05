package com.example.footballplayassistant.presentation.customviews.cards


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun CommonIconTextCard(modifier: Modifier = Modifier, icon: Int, text: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = "DateTime",
                modifier = Modifier
                    .padding(8.dp)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun CommonIconTextInventoryCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.inventory),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
            )
            Row {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_manish_18_22),
                    contentDescription = "Manishka",
                    modifier = Modifier
                        .padding(8.dp)
                )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_ball_22),
                    contentDescription = "Ball",
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun CommonOtherInfoCard(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.otherInfo),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W500),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
                    .weight(0.75f)
            )

            IconButton(
                onClick = {}, colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.weight(0.25f)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_white_arrows_18),
                    contentDescription = "Arrows",
                )
            }
        }
    }
}

@Composable
fun CommonWithdrawalSumCard(modifier: Modifier = Modifier, summa: Int, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.7f)) {
                Text(
                    text = stringResource(id = R.string.summaWithdrawal),
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W500),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(8.dp)
                )
                Row{
                    Text(
                        text = "$summa",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 8.dp, end = 2.dp, bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.currency),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            IconButton(
                onClick = {}, colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.align(Alignment.Bottom).padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_white_arrows_18),
                    contentDescription = "Arrows",
                )
            }
        }
    }
}