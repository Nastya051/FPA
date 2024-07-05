package com.example.footballplayassistant.presentation.ui.screens.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.constants.RulesPolitic
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun RulesAndPoliticScreen(header: String) {
    val navController = LocalNavController.current!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp)
    ) {
        HeaderWithBackButton(text = stringResource(id = if (header == RulesPolitic.RULES) R.string.rulesHeader
        else R.string.politicHeader),
            onClickBack = { navController.navigate(Route.SignInScreen.path) })

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            item {
                Text(
                    text = stringResource(id = R.string.generalProvisions),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding()
                )

                Text(
                    text = stringResource(id = R.string.firstBlock),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.W400,
                        lineHeight = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.basicConcepts),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = stringResource(id = R.string.secondBlock),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.W400,
                        lineHeight = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}