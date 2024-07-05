package com.example.footballplayassistant.presentation.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route


@Composable
@Preview
fun StartScreen() {
    val navController = LocalNavController.current!!
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.player),
            contentDescription = "Player",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        Card(
            modifier = Modifier
                .fillMaxHeight(0.54f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(ScrollState(0))
            )
            {
                val (column, leftImage, rightImage) = createRefs()
                val horizontalLine = createGuidelineFromTop(0.4f)
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_ball_144_223),
                    contentDescription = "Ball left",
                    modifier = Modifier.constrainAs(ref = leftImage) {
                        top.linkTo(parent.top, margin = 0.dp)
                    }
                )

                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_ball_140_170),
                    contentDescription = "Ball right",
                    modifier = Modifier.constrainAs(ref = rightImage) {
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        end.linkTo(parent.end)
                    }
                )
                Column(modifier = Modifier
                    .constrainAs(ref = column) {
                        top.linkTo(horizontalLine)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.takePart),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelLarge
                            .copy(fontWeight = FontWeight.W400),
                        modifier = Modifier.padding(bottom = 30.dp, start = 16.dp, end = 16.dp)
                    )

                    CommonButton(
                        text = stringResource(id = R.string.signin),
                        style = MaterialTheme.typography.bodyLarge,
                        onClick = { navController.navigate(Route.SignInScreen.path) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Button(modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        onClick = { navController.navigate(Route.SignUpEnterPhoneScreen.path) }) {
                        Text(
                            text = stringResource(id = R.string.signup),
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontWeight = FontWeight.W600),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_arrows_18_14),
                            contentDescription = "Arrows"
                        )
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Center loge",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(0.3f)
        )
    }
}

