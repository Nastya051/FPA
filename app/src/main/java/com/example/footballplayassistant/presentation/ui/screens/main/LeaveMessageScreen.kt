package com.example.footballplayassistant.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.screens.search_tab.NecessaryTextField
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun LeaveMessageScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(MaterialTheme.spacing.medium)) {
        var isEmpty by remember { mutableStateOf(true) }
        HeaderWithBackButton(text = stringResource(R.string.leaveMessageHeader))
        Spacer(modifier = Modifier.size(16.dp))
        NecessaryTextField(label = stringResource(R.string.yourMessagePlaceholderText), toCountWords = 255, isSingleLine = false, removeLabelAbove = true,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(12.dp),
            isEmpty = { isEmpty = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        CommonButton(stringResource(R.string.sendButtonText), enable = !isEmpty)
    }

}