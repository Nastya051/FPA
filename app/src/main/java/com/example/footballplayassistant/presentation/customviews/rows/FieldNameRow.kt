package com.example.footballplayassistant.presentation.customviews.rows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun FieldNameRow(modifier: Modifier = Modifier, fieldName: String) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .padding(top = 10.dp)
        .clickable { }) {
        Text(
            text = fieldName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.W500, fontFamily = FontFamily(
                    Font(R.font.inter_medium)
                )
            ),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_next_24),
            contentDescription = "Arrow next",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
    }
}