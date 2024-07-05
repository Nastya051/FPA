package com.example.footballplayassistant.presentation.customviews.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.footballplayassistant.R

@Composable
fun ForgotPassword(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    ClickableText(
        text = AnnotatedString(text = stringResource(id = R.string.forgotPassword)),
        style = MaterialTheme.typography.displaySmall.copy(
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.End
        ),
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick.invoke() }
    )
}