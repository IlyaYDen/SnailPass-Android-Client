package com.example.snailpasswordmanager.presentation.accountInfo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains


@Preview
@Composable
fun SearchPanelPreview(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xff202327),
                    1f to Color(0xff26292d),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {

        val value = remember { mutableStateOf("test") }
        Panel(value,{},{})
    }
}


@Composable
fun Panel(
    value: MutableState<String>,
    onMenuClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean = false
) {

    val favorite = remember { mutableStateOf(isFavorite) }
    val warning = remember { mutableStateOf("") }
    val empty_error = stringResource(id = R.string.empty_error)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .weight(1f)
        ) {
            Image(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Vector",
                modifier = Modifier
                    .size(48.dp)
                    .clickable(onClick = onMenuClick),
                colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.3f))
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            CustomTextField(
                value = value,
                onValueChange = {
                    value.value = it
                    if(it.isEmpty()) warning.value = empty_error
                    else warning.value = ""
                },
                warning = warning,
                modifier = Modifier
                    .wrapContentSize(),

                enteredTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
                hintTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(end = 8.dp)
        ) {
            CustomImageButton(
                image = Icons.Filled.Star,
                onClick = {
                    onFavoriteClick()
                    favorite.value = !favorite.value
                },
                trigger = favorite
            )
        }
    }
}