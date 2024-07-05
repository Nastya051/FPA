package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.screens.authentication.addStar
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun FeedbackScreen() {
    val navController = LocalNavController.current!!
    val buttonEnable = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    val description = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    DialogScreen(
        header = stringResource(id = R.string.sendRequestSuccessfully),
        description = stringResource(id = R.string.youWillBeContacted),
        whiteButton = stringResource(id = R.string.returnToMain),
        image = R.drawable.ic_check_92,
        onClickWhite = { navController.navigate(Route.MainScreen.path) },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            HeaderWithBackButton(
                text = stringResource(id = R.string.feedback),
                onClickBack = { navController.navigate(Route.AboutAppScreen.path) },
                modifier = Modifier.padding(top = 12.dp, bottom = 16.dp)
            )

            Text(
                text = addStar(id = R.string.suggestionOrProblem),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )

            Box {
                CommonTextField(
                    placeholder = stringResource(id = R.string.describeYourProblems),
                    singleLine = false,
                    cornerRadius = 20.dp,
                    maxLength = 300,
                    onClick = { description.value = it },
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_rezible_10),
                    contentDescription = "Rezible",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(
                            end = MaterialTheme.spacing.small,
                            bottom = MaterialTheme.spacing.small
                        )
                )
            }

            Text(
                text = addStar(id = R.string.EmailBigE),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )

            CommonTextField(
                placeholder = stringResource(id = R.string.EmailEnter),
                keyBoard = KeyboardType.Text,
                color = MaterialTheme.colorScheme.primaryContainer,
                onClick = { email.value = it },
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

        LaunchedEffect(key1 = description.value, key2 = email.value) {
            buttonEnable.value = description.value.isNotEmpty() && email.value.isNotEmpty()
        }

        val animatedContainerColor: Color by animateColorAsState(
            targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.tertiary,
            animationSpec = tween(500, 0, LinearEasing)
        )
        val animatedContentColor: Color by animateColorAsState(
            targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSecondaryContainer,
            animationSpec = tween(500, 0, LinearEasing)
        )
        CommonButton(
            text = stringResource(id = R.string.send),
            containerColor = animatedContainerColor,
            contentColor = animatedContentColor,
            enable = buttonEnable.value,
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}