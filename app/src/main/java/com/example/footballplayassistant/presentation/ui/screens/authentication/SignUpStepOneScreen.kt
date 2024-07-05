package com.example.footballplayassistant.presentation.ui.screens.authentication

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.auth.UserRegistrationStepOne
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.headers.HeaderAuthentication
import com.example.footballplayassistant.presentation.customviews.headers.HeaderSignUpStep
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.viewmodels.AuthenticationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpStepOneScreen() {
    val context = LocalContext.current
    val navController = LocalNavController.current!!
    val viewModel: AuthenticationViewModel = getViewModel()
    val buttonEnable by viewModel.isButtonEnable.collectAsState(initial = false)
    val nickname by viewModel.nickname.collectAsState()
    val name by viewModel.name.collectAsState()
    val surname by viewModel.surname.collectAsState()
    val email by viewModel.email.collectAsState(initial = "")
    val isAllUnique by viewModel.isAllUnique.collectAsState()
    val isServerError by viewModel.isServerError.collectAsState(initial = false)
    val isSendRequest by viewModel.isSendRequest.collectAsState(initial = false)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {
        HeaderAuthentication {
            HeaderSignUpStep(numStep = 1,
                onClick = { navController.navigate(Route.SignUpEnterPhoneScreen.path) })
        }

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            item {
                Text(
                    text = stringResource(R.string.writeInfo), textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = addStar(id = R.string.nick),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                )

                CommonTextField(
                    placeholder = stringResource(id = R.string.enterNick),
                    keyBoard = KeyboardType.Text,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    maxLength = 10,
                    onClick = { viewModel.updateNickname(it) },
                    isError = !isAllUnique && nickname.isNotEmpty() && isSendRequest,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            item {
                AnimatedVisibility(visible = !isAllUnique && nickname.isNotEmpty() && isSendRequest) {
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
                            text = stringResource(id = R.string.nicknameIsOccupied),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.W400
                            ),
                            color = MaterialTheme.colorScheme.errorContainer
                        )
                    }
                }
                AnimatedVisibility(visible = nickname.isEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, bottom = 10.dp)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_warning_12),
                            contentDescription = "Warning",
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.nickLength),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.W400
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }

            item {
                Text(
                    text = addStar(id = R.string.name),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                )

                CommonTextField(
                    placeholder = stringResource(id = R.string.enterName),
                    keyBoard = KeyboardType.Text,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onClick = { viewModel.updateName(it) },
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            item {
                Text(
                    text = addStar(id = R.string.surname),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                )

                CommonTextField(
                    placeholder = stringResource(id = R.string.enterSurname),
                    keyBoard = KeyboardType.Text,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onClick = { viewModel.updateSurname(it) },
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            item {
                Text(
                    text = addStar(id = R.string.email),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                )

                CommonTextField(
                    placeholder = stringResource(id = R.string.enterEmail),
                    keyBoard = KeyboardType.Email,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    isError = !isAllUnique && email.isNotEmpty() && isSendRequest,
                    onClick = { viewModel.updateEmail(it) }
                )
                AnimatedVisibility(visible = !isAllUnique && email.isNotEmpty() && isSendRequest) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, bottom = 10.dp, top = 2.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_warning_12),
                            contentDescription = "Warning",
                            tint = MaterialTheme.colorScheme.errorContainer,
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.emailIsOccupied),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.W400
                            ),
                            color = MaterialTheme.colorScheme.errorContainer
                        )
                    }
                }
            }

            item {
                LaunchedEffect(nickname, name, surname, email) {
                    viewModel.updateButtonEnable(
                        nickname.isNotEmpty() && name.isNotEmpty() &&
                                surname.isNotEmpty() && email.isNotEmpty()
                    )
                }
                LaunchedEffect(isAllUnique) {
                    if(isAllUnique)
                        navController.navigate(Route.SignUpStepTwoScreen.withArgs(nickname,name, surname,email))
                }
                val str = stringResource(id = R.string.smthGoWrong)
                LaunchedEffect(isServerError) {
                    if (isServerError)
                        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
                }
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
                    text = context.getString(R.string.next),
                    containerColor = animatedContainerColor,
                    contentColor = animatedContentColor,
                    enable = buttonEnable,
                    onClick = {
                        viewModel.signUpStepOne(
                            UserRegistrationStepOne(
                                nickname = nickname,
                                name = name,
                                surname = surname,
                                email = email
                            )
                        )
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 36.dp)
                )
            }
        }
    }
}

@Composable
fun addStar(
    id: Int,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.W500,
    fontFamily: FontFamily = FontFamily(Font(R.font.inter)),
    color: Color = MaterialTheme.colorScheme.onSecondaryContainer
): AnnotatedString {
    val text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily
            )
        ) {
            append(stringResource(id))
        }

        pushStringAnnotation(tag = "star", annotation = "star")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = fontFamily,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        ) {
            append("*")
        }
        pop()
    }
    return text
}