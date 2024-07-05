package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R
enum class FilterCurrentArchive(@StringRes val filter: Int) {
    Current(R.string.current),
    Archive(R.string.archive)
}

@Composable
fun getFilterCurrentArchiveString(f: FilterCurrentArchive): String {
    return stringResource(id = f.filter)
}

@Composable
fun getFiltersCurrentArchive(): List<String> {
    return listOf(
        getFilterCurrentArchiveString(f = FilterCurrentArchive.Current),
        getFilterCurrentArchiveString(f = FilterCurrentArchive.Archive)
    )
}