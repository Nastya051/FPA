package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R

enum class LevelPlay(@StringRes val level: Int) {
    Beginner(R.string.beginner),
    Amateur(R.string.amateur),
    Experienced(R.string.experienced),
    Professional(R.string.professional)
}

@Composable
private fun getLevel(lev: LevelPlay): String {
    return stringResource(id = lev.level)
}

@Composable
fun getLevels(): List<String> {
    return listOf(
        getLevel(lev = LevelPlay.Beginner), getLevel(lev = LevelPlay.Amateur),
        getLevel(lev = LevelPlay.Experienced), getLevel(lev = LevelPlay.Professional)
    )
}