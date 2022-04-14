package com.faraji.sanags.core.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.faraji.sanags.R

// Set of Material typography styles to start with
val iranYekan = FontFamily(
    Font(R.font.iran_yekan_bold, FontWeight.Bold),
    Font(R.font.iran_yekan_medium, FontWeight.Medium),
    Font(R.font.iran_yekan_regular, FontWeight.Normal)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = iranYekan,
        fontWeight = FontWeight.Normal,
        textDirection = TextDirection.Rtl,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = iranYekan,
        fontWeight = FontWeight.Normal,
        textDirection = TextDirection.Rtl,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = iranYekan,
        fontWeight = FontWeight.Bold,
        textDirection = TextDirection.Rtl,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = iranYekan,
        fontWeight = FontWeight.Medium,
        textDirection = TextDirection.Rtl,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = iranYekan,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        textDirection = TextDirection.Rtl
    )
)