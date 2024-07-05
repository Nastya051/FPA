package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.buttons.ForgotPassword
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.screens.authentication.addStar

@Composable
fun SafetyScreen() {
    val navController = LocalNavController.current!!
    val buttonEnable = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    val currentPassword = remember { mutableStateOf("") }
    val newPasswordFirst = remember { mutableStateOf("") }
    val newPasswordSecond = remember { mutableStateOf("") }

    DialogScreen(
        header = stringResource(id = R.string.changedPasswordSuccess),
        description = stringResource(id = R.string.needAutorization),
        whiteButton = stringResource(id = R.string.loginAgain),
        image = R.drawable.ic_check_92,
        onClickWhite = { navController.navigate(Route.SignInScreen.path) },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            HeaderWithBackButton(
                text = stringResource(id = R.string.safety),
                onClickBack = {navController.navigate(Route.UserProfileScreen.withArgs("false"))},
                modifier = Modifier.padding(top = 12.dp)
            )

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                item {
                    Text(
                        text = stringResource(id = R.string.exitFromAll),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.W400),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                }
                item {
                    Text(
                        text = addStar(id = R.string.currentPassword),
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.W500),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 8.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.currentPassword),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        isPassword = true,
                        imageTrail = R.drawable.ic_eye_slash_24,
                        tintIcon = MaterialTheme.colorScheme.secondary,
                        onClick = { currentPassword.value = it },
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                item {
                    Text(
                        text = addStar(id = R.string.newPassword),
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.W500),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 8.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.enterNewPass),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        isPassword = true,
                        imageTrail = R.drawable.ic_eye_slash_24,
                        tintIcon = MaterialTheme.colorScheme.secondary,
                        onClick = { newPasswordFirst.value = it },
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    AnimatedVisibility(
                        visible = newPasswordFirst.value.isNotEmpty()
                                && (newPasswordFirst.value.length < 8
                                || !newPasswordFirst.value.contains("[A-Z]".toRegex())
                                || !newPasswordFirst.value.contains("[a-z]".toRegex())
                                || !newPasswordFirst.value.contains("[0-9]".toRegex())),
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 1000))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 10.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_warning_12),
                                contentDescription = "Warning",
                                tint = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.symbolsNumbers),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.W400),
                                color = MaterialTheme.colorScheme.errorContainer
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = addStar(id = R.string.newPasswordOneMore),
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.W500),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 8.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.enterNewPasswordOneMore),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        isPassword = true,
                        imageTrail = R.drawable.ic_eye_slash_24,
                        tintIcon = MaterialTheme.colorScheme.secondary,
                        onClick = { newPasswordSecond.value = it },
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    AnimatedVisibility(
                        visible = newPasswordFirst.value != newPasswordSecond.value
                                && newPasswordSecond.value.isNotEmpty(),
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 1000))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 10.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_warning_12),
                                contentDescription = "Warning",
                                tint = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.passwordsDifferent),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.W400),
                                color = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier
                            )
                        }
                    }
                }
                item {
                    ForgotPassword(onClick = {
                        navController.navigate(Route.ForgotPasswordScreen.path)
                    })
                }
            }
        }

        LaunchedEffect(key1 = newPasswordFirst.value, key2 = newPasswordSecond.value,
            key3 = currentPassword.value){
            if (newPasswordFirst.value == newPasswordSecond.value && newPasswordFirst.value.isNotEmpty()
                && currentPassword.value.isNotEmpty())
                buttonEnable.value = true
            else
                buttonEnable.value = false
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
            text = stringResource(id = R.string.changePassword),
            containerColor = animatedContainerColor,
            contentColor = animatedContentColor,
            enable = buttonEnable.value,
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}