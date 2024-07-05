package com.example.footballplayassistant.presentation.customviews.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun HeaderAuthentication(header: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 12.dp,
            bottomStart = 12.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_ball_full),
                contentDescription = "Ball left",
                modifier = Modifier.align(Alignment.TopStart)
            )

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_ball_full_79_87),
                contentDescription = "Ball right",
                modifier = Modifier.align(Alignment.BottomEnd)
            )

            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                header()
            }
        }
    }
}

@Composable
@Preview
fun HeaderSignIn() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.medium, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_header), contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.3f), alignment = Alignment.CenterStart
            )
            Button(colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.outlineVariant
            ), modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.skip),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Text(
            text = stringResource(id = R.string.enter),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
fun HeaderSignUp() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.medium, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_header), contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.3f), alignment = Alignment.CenterStart
            )
            Button(colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.outlineVariant
            ),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.skip),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.W500),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Text(
            text = stringResource(id = R.string.registration),
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun HeaderSignUpCode(textResource: Int) {
    Text(
        text = stringResource(id = textResource),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.W500),
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp))
}

@Composable
fun HeaderSignUpStep(numStep: Int, onClick: () -> Unit = {}) {
    Column {
        HeaderWithBackButton(
            text = stringResource(id = R.string.registration), tint = MaterialTheme.colorScheme.onPrimary,
            styleText = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.W500),
            colorText = MaterialTheme.colorScheme.onPrimary,
            onClickBack = onClick,
            modifier = Modifier.padding(top = 20.dp, bottom = 35.dp, start = 16.dp, end = 16.dp))

        Text(
            text = stringResource(id = R.string.step, numStep),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun HeaderForgotPassword(onClick: () -> Unit = {}) {
    HeaderWithBackButton(
        text = stringResource(id = R.string.forgotPassword), tint = MaterialTheme.colorScheme.onPrimary,
        styleText = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.W500),
        colorText = MaterialTheme.colorScheme.onPrimary,
        onClickBack = onClick,
        modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp))
}

@Composable
fun HeaderRecoveryPassword(onClick: () -> Unit = {}) {
    HeaderWithBackButton(
        text = stringResource(id = R.string.recoveryPassword), tint = MaterialTheme.colorScheme.onPrimary,
        styleText = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.W500),
        colorText = MaterialTheme.colorScheme.onPrimary,
        onClickBack = onClick,
        modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp))
}