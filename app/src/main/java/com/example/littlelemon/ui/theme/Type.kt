package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    //Display title
    titleLarge = TextStyle(
        fontFamily = markaziFont,
        fontWeight = FontWeight.Medium,
        fontSize = 54.sp
    ),
    //Sub title
    titleMedium = TextStyle(
        fontFamily = markaziFont,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    //section title, to make uppercase
    titleSmall = TextStyle(
        fontFamily = karlaFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    //Section categories
    labelLarge = TextStyle(
        fontFamily = karlaFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    //card title
    labelMedium = TextStyle(
        fontFamily = karlaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    //lead text
    bodyLarge = TextStyle(
        fontFamily = karlaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    //highlight text
    bodyMedium = TextStyle(
        fontFamily = karlaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Primary
    ),
    //paragraph text
    bodySmall = TextStyle(
        fontFamily = karlaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Primary
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)