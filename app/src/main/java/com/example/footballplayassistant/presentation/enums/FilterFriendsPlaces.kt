package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R

enum class FilterFriendsPlaces(@StringRes val filter: Int) {
    Friends(R.string.friends),
    Places(R.string.places),
}

@Composable
fun getFilterFriendsPlacesString(f: FilterFriendsPlaces): String {
    return stringResource(id = f.filter)
}

@Composable
fun getFiltersFriendsPlaces(): List<String> {
    return listOf(
        getFilterFriendsPlacesString(f = FilterFriendsPlaces.Friends),
        getFilterFriendsPlacesString(f = FilterFriendsPlaces.Places)
    )
}