package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R

enum class FilterPhoneEmail(@StringRes val way: Int) {
    Phone(R.string.byPhone),
    Email(R.string.byEmail),
}

@Composable
fun getFilterPhoneEmailString(f: FilterPhoneEmail): String {
    return stringResource(id = f.way)
}


@Composable
fun getFilters(): List<String> {
    return listOf(
        getFilterPhoneEmailString(f = FilterPhoneEmail.Phone),
        getFilterPhoneEmailString(f = FilterPhoneEmail.Email)
    )
}