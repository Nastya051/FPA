package com.example.footballplayassistant.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 36.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 32.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_semibold)),
        fontSize = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_semibold)),
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 16.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_semibold)),
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_semibold)),
        fontSize = 14.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 14.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontSize = 14.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_semibold)),
        fontSize = 12.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 10.sp,
    ),
)