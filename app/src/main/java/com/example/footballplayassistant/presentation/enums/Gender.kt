package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footballplayassistant.R

enum class Gender(@StringRes val gender: Int) {
    Man(R.string.man),
    Woman(R.string.woman),
}

@Composable
private fun getGender(g: Gender): String {
    return stringResource(id = g.gender)
}

@Composable
fun getGenders(): List<String> {
    return listOf(getGender(g = Gender.Man), getGender(g = Gender.Woman))
}