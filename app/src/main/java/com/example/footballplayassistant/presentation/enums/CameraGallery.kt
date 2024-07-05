package com.example.footballplayassistant.presentation.enums

import androidx.annotation.StringRes
import com.example.footballplayassistant.R

enum class CameraGallery(@StringRes val type: Int) {
    Camera(R.string.camera),
    Gallery(R.string.gallery)
}