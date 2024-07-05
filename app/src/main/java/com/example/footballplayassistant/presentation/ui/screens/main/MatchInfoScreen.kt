package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
@Preview
fun MatchInfoScreen(/*type: String, name: String, description: String*/) {
    val navController = LocalNavController.current!!
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimary)) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.matchInfo),
            onClickBack = { navController.navigate(Route.MatchScreen.path) },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp)
        )

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            item {
                Text(
                    text = "game type",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }

            item {
                Text(
                    text = "game Name",
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "game description",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.matchPlace),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                //карта
            }
        }
    }
}