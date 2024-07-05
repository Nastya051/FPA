package com.example.footballplayassistant.presentation.ui.screens.authentication

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.models.auth.UserEmail
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.constants.PhoneEmail
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.headers.HeaderAuthentication
import com.example.footballplayassistant.presentation.customviews.headers.HeaderForgotPassword
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.viewmodels.AuthenticationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ForgotPasswordScreen() {
    val navController = LocalNavController.current!!
    val context = LocalContext.current
    val viewModel: AuthenticationViewModel = getViewModel()
    val buttonEnable by viewModel.isButtonEnable.collectAsState(initial = false)
    val email by viewModel.email.collectAsState(initial = "")
    val isServerError by viewModel.isServerError.collectAsState(initial = false)
    val isSendRequest by viewModel.isSendRequest.collectAsState(initial = false)

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderAuthentication {
            HeaderForgotPassword(onClick = { navController.navigate(Route.SignInScreen.path) })
        }

        LazyColumn {
            item {
                Text(
                    text = stringResource(id = R.string.instructionRecoveryPassword),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
            }

            item {
                CommonTextField(
                    placeholder = stringResource(id = R.string.enterEmail),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onClick = {
                        viewModel.updateButtonEnable(it.isNotEmpty())
                        viewModel.updateEmail(it)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                val animatedContainerColor: Color by animateColorAsState(
                    targetValue = if (buttonEnable) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.tertiary,
                    animationSpec = tween(500, 0, LinearEasing)
                )
                val animatedContentColor: Color by animateColorAsState(
                    targetValue = if (buttonEnable) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSecondaryContainer,
                    animationSpec = tween(500, 0, LinearEasing)
                )
                CommonButton(
                    text = stringResource(id = R.string.sendCode),
                    containerColor = animatedContainerColor,
                    contentColor = animatedContentColor,
                    enable = buttonEnable,
                    onClick = { viewModel.sendCodeToEmail(email = UserEmail(email = email)) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                )

                LaunchedEffect(isSendRequest) {
                    if (isSendRequest)
                        navController.navigate(Route.SignUpCodeScreen.withArgs(PhoneEmail.EMAIL))
                }
                val str = stringResource(id = R.string.smthGoWrong)
                LaunchedEffect(isServerError) {
                    if (isServerError)
                        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}