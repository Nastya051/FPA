package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R

enum class FilterPlayersOrganizers(@StringRes val type: Int) {
    Players(R.string.forPlayers),
    Organizers(R.string.forOrganizers)
}

@Composable
fun getFilterPlayersOrganizersString(f: FilterPlayersOrganizers): String {
    return stringResource(id = f.type)
}

@Composable
fun getFiltersPlayersOrganizers(): List<String> {
    return listOf(
        getFilterPlayersOrganizersString(f = FilterPlayersOrganizers.Players),
        getFilterPlayersOrganizersString(f = FilterPlayersOrganizers.Organizers)
    )
}