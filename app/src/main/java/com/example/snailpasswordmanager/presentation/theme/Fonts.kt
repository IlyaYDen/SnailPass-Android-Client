package com.example.snailpasswordmanager.presentation.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.snailpasswordmanager.R


    val appFontJetBrains = FontFamily(
        fonts = listOf(
            Font(
                resId = R.font.jet_brains_mono_regular,
                FontWeight.Normal,
                FontStyle.Normal
            ),
            Font(
                resId = R.font.j_et_brains_mono_italic,
                FontWeight.Normal,
                FontStyle.Italic
            )
        )
    )
