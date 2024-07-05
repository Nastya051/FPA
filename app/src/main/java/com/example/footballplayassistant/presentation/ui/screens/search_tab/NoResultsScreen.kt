package com.example.footballplayassistant.presentation.ui.screens.search_tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.ui.theme.spacing


@Composable
fun NoResultsScreen(title : String, description : String, buttonText: String, isAuthorized : Boolean = true, onClick : () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center) {
            Text(
                text = title, style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                text = description,
                textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
        Column {
            CommonButton(
                text = buttonText,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = onClick
            )
            if (!isAuthorized) {
                Text(
                    stringResource(R.string.loginToCreateGame),
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium).align(Alignment.CenterHorizontally))
            }
        }

    }
}