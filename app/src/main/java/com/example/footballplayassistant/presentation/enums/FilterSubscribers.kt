package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R

enum class FilterSubscribers(@StringRes val filter: Int) {
    Subscribers(R.string.subscribers),
    Subscriptions(R.string.subscriptions)
}

@Composable
fun getFilterSubscribersString(f: FilterSubscribers): String {
    return stringResource(id = f.filter)
}

@Composable
fun getFiltersSubscribers(): List<String> {
    return listOf(
        getFilterSubscribersString(f = FilterSubscribers.Subscribers),
        getFilterSubscribersString(f = FilterSubscribers.Subscriptions)
    )
}