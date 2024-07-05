package com.example.footballplayassistant.presentation.customviews.headers

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun HeaderWithBackButton(
    text: String,
    imageButton: Int = 0,
    onClickBack: () -> Unit = {},
    onClickOther: () -> Unit = {},
    actionsMenu: @Composable () -> Unit = {},
    tint: Color = MaterialTheme.colorScheme.primary,
    styleText: TextStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
    colorText: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(modifier = Modifier
            .weight(0.1f)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = CircleShape
            ),
            onClick = onClickBack) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow_10_18),
                contentDescription = "Back arrow",
                tint = tint
            )
        }

        Text(
            text = text,
            style = styleText,
            color = colorText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.8f)
        )

        if (imageButton != 0)
            IconButton(modifier = Modifier
                .weight(0.1f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = CircleShape
                ),
                onClick = onClickOther) {
                Icon(
                    imageVector = ImageVector.vectorResource(imageButton),
                    contentDescription = "Image",
                    tint = tint
                )
                actionsMenu()
            }
        else
            IconButton(modifier = Modifier.weight(0.1f).border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = CircleShape
            ),
                onClick = { /*TODO*/ }) {
            }
    }
}