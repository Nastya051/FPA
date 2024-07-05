package com.example.footballplayassistant.presentation.customviews.rows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
fun SubscriberRow(
    text: String,
    name: String,
    foto: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserFotoForList(
            text = text, name = name, foto = foto,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next_10_18),
            contentDescription = "Arrow",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(0.2f)
        )
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.fillMaxWidth()
    )
}