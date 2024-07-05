package com.example.footballplayassistant.presentation.customviews.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R


@Composable
fun CommentsCard(modifier: Modifier = Modifier, commentsList: List<Int>) {
    Card(
        modifier = modifier.padding(top = 32.dp),
        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp,
            bottomStart = if (commentsList.size > 1) 0.dp else 32.dp,
            bottomEnd = if (commentsList.size > 1) 0.dp else 32.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.comments),
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.W600),
                    modifier = Modifier.padding(end = 4.dp)
                )

                ClickableText(
                    text = AnnotatedString(text = stringResource(id = R.string.sendMessage)),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    onClick = {})
            }

            var countComments = 0
            countComments = when (commentsList.size) {
                1, 2, 3 -> commentsList.size - 1
                else -> 2
            }

            if (commentsList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .heightIn(min = 200.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.noMessages),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 24.dp)
                    )
                }
            } else
                for (i in 0..countComments) {
                    CurrentCommentCard(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 24.dp),
                        date = "10 hours",
                        name = "NAme NAme",
                        likes = 15,
                        text = """Манишки есть, брать ничего не надо, все взял.
                        | Давайте пораньше придем минут на 15 чтоб подготовиться""".trimMargin()
                    )
                }
        }
    }
}