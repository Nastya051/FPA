package com.example.footballplayassistant.presentation.customviews.rows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.constants.RulesPolitic
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
@Preview
fun BlockRules(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current!!
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        val text = buildAnnotatedString {
            append(stringResource(R.string.blockrulesstart))
            append(" ")

            pushStringAnnotation(tag = RulesPolitic.RULES, annotation = RulesPolitic.RULES)
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.secondary,
                )
            ) {
                append(stringResource(R.string.rules))
                append(" ")
            }
            pop()

            append(stringResource(id = R.string.and))
            append(" ")

            pushStringAnnotation(tag = RulesPolitic.POLITIC, annotation = RulesPolitic.POLITIC)
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.secondary
                )
            )
            {
                append(stringResource(R.string.politic))
            }
            pop()

        }

        ClickableText(text = text,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            ),
            onClick = { offset ->
                text.getStringAnnotations(tag = RulesPolitic.RULES, start = offset, end = offset).firstOrNull()
                    ?.let {
                        navController.navigate(Route.RulesAndPoliticScreen.withArgs(RulesPolitic.RULES))
                    }
                text.getStringAnnotations(tag = RulesPolitic.POLITIC, start = offset, end = offset)
                    .firstOrNull()
                    ?.let {
                        navController.navigate(Route.RulesAndPoliticScreen.withArgs(RulesPolitic.POLITIC))
                    }
            })
    }
}