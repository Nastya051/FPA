package com.example.footballplayassistant.presentation.customviews.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.footballplayassistant.R

@Composable
fun ShowMore(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.outlineVariant
        ),
            onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.showmore),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
            )
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrows_down_24),
                contentDescription = "Arrows down"
            )
        }
    }
}