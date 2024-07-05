package com.example.footballplayassistant.presentation.ui.screens.profile

import android.os.Build.VERSION
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.BuildConfig
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.cards.ActionsCard
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun AboutAppScreen() {
    val navController = LocalNavController.current!!
    val showDialogExit = remember { mutableStateOf(false) }
    val showDialogDelete = remember { mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.exitQuestion),
        description = stringResource(id = R.string.enterDataAfterExit),
        greenButton = stringResource(id = R.string.exit),
        whiteButton = stringResource(id = R.string.cancel),
        onClickGreen = { navController.navigate(Route.SignInScreen.path) },
        onClickWhite = { showDialogExit.value = false },
        onDismissRequest = { showDialogExit.value = false },
        showDialog = showDialogExit.value
    )

    DialogScreen(
        header = stringResource(id = R.string.deleteQuestion),
        description = stringResource(id = R.string.lostDataAfterDelete),
        greenButton = stringResource(id = R.string.delete),
        whiteButton = stringResource(id = R.string.cancel),
        onClickGreen = { navController.navigate(Route.StartScreen.path) },
        onClickWhite = { showDialogDelete.value = false },
        onDismissRequest = { showDialogDelete.value = false },
        showDialog = showDialogDelete.value
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.aboutApp),
            onClickBack = { navController.navigate(Route.UserProfileScreen.withArgs("false")) },
            modifier = Modifier.padding(top = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo_fpa_66),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(top = 31.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
            item {
                Texts(
                    greyText = stringResource(id = R.string.versionApp),
                    blackText = BuildConfig.VERSION_CODE.toString()
                )
            }
            item {
                Texts(greyText = stringResource(id = R.string.versionOS),
                    blackText = VERSION.RELEASE)
            }
            item {
                Texts(greyText = stringResource(id = R.string.region), blackText = "rus")
            }
            item {
                ActionsCard(
                    iconTextList = listOf(
                        Pair(
                            R.drawable.ic_feedback_24,
                            stringResource(id = R.string.feedback)
                        ),
                        Pair(
                            R.drawable.ic_confidentiality_24,
                            stringResource(id = R.string.confidentiality)
                        ),
                        Pair(
                            R.drawable.ic_faq_24,
                            stringResource(id = R.string.faq)
                        ),
                    ),
                    actionsList = listOf(
                        { navController.navigate(Route.FeedbackScreen.path) },
                        {/*navigate*/ },
                        { navController.navigate(Route.FAQScreen.path) }),
                    cardColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }

            item {
                ClickableText(
                    text = AnnotatedString(
                        text = stringResource(id = R.string.exit),
                        spanStyle = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            textAlign = TextAlign.Center).toSpanStyle()),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 22.dp)
                ) {
                    showDialogExit.value = true
                }
            }
            item {
                ClickableText(
                    text = AnnotatedString(
                        text = stringResource(id = R.string.deleteAcc),
                        spanStyle = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            textAlign = TextAlign.Center).toSpanStyle()),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    showDialogDelete.value = true
                }
            }
        }
    }
}

@Composable
private fun Texts(greyText: String, blackText: String) {
    Text(
        text = greyText,
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxWidth()
    )

    Text(
        text = blackText,
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
        color = MaterialTheme.colorScheme.primary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    )
}