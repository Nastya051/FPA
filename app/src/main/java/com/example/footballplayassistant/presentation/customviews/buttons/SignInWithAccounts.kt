package com.example.footballplayassistant.presentation.customviews.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R

@Composable
@Preview
fun SignInWithAccounts(modifier: Modifier = Modifier) {
    Column(modifier = modifier.wrapContentSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
                thickness = 1.dp, color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = stringResource(id = R.string.or), textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp),
                thickness = 1.dp, color = MaterialTheme.colorScheme.outline
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 24.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            SquareButton(
                text = stringResource(id = R.string.google),
                image = R.drawable.ic_google_25
            )
            SquareButton(
                text = stringResource(id = R.string.vk), image = R.drawable.ic_vk_29_16,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            SquareButton(
                text = stringResource(id = R.string.yandex),
                image = R.drawable.ic_yandex_13_24
            )
        }
    }
}

@Composable
fun SquareButton(modifier: Modifier = Modifier, text: String, image: Int) {
    Button(modifier = modifier
        .heightIn(min = 66.dp)
        .widthIn(min = 66.dp),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = { /*TODO*/ }) {
        Column(modifier = Modifier) {
            Image(
                modifier = Modifier
                    .height(25.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 4.dp),
                imageVector = ImageVector.vectorResource(image),
                contentDescription = "Logo"
            )
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}