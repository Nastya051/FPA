package com.example.footballplayassistant.presentation.customviews.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun ButtonCalendar(
    modifier: Modifier = Modifier,
    onCLick: () -> Unit,
    date: String,
    placeholder: String,
    containerColor: Color
) {
    Button(
        onClick = onCLick,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_22),
                    contentDescription = "Calendar",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = if (date.isEmpty()) placeholder else date,
                    color = if (date.isEmpty()) MaterialTheme.colorScheme.onSecondaryContainer
                    else MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_menu_18_10),
                contentDescription = "Down arrow"
            )
        }
    }
}