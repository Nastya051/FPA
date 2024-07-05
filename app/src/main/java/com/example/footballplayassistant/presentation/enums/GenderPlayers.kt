package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import com.example.footballplayassistant.R

enum class GenderPlayers(@StringRes val gender: Int)  {
    Men(R.string.men),
    Women(R.string.women),
    Both(R.string.mw),
}