package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import com.example.footballplayassistant.R

enum class BottomMenu(@StringRes val element: Int) {
    Home(R.string.home),
    Search(R.string.search),
    Calendar(R.string.calendar),
    Profile(R.string.profile)
}