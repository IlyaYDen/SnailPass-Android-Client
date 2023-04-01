package com.example.snailpasswordmanager.presentation.recordList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.core.components.CustomTextFieldWidth
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains

///todo internet, registration, notes
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

        SearchPanel({}, {}, {}, {}, remember {mutableStateOf("")},"test")
    }
}


@Composable
fun SearchPanel(
    onMenuClick : () -> Unit,
    onFavoriteClick : () -> Unit,
    onDeletedClick : () -> Unit,
    onSearchClick : () -> Unit,
    searchValue : MutableState<String>,
    name: String
) {

    val favorite = remember { mutableStateOf(false) }
    val deleted = remember { mutableStateOf(false) }
    val search = remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier.height(45.dp)
    ) {
        if(search.value){
            CustomTextField(
                value =searchValue,
                onValueChange = {
                    searchValue.value = it
                },
                modifier = Modifier.padding(end = 73.dp,start = 2.dp)//,top = 2.dp, bottom = 2.dp)
                    .focusRequester(focusRequester),
            )

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //if (!search.value) {
                if (!search.value) {searchValue.value = ""}
                Image(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Vector",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(onClick = onMenuClick),
                    colorFilter = ColorFilter.tint(
                        if(!search.value) Color.White.copy(alpha = 0.3f)
                        else Color.White.copy(alpha = 0.0f)
                    )
                )
                    Spacer(
                        modifier = Modifier
                            .width(width = 12.dp)
                    )
                    Text(
                        text = name,
                        color = if(!search.value) Color.White
                                else Color.White.copy(alpha = 0.0f)
                        ,
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = appFontJetBrains
                        )
                    )
                //}
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {
                /*
            onMenuClick
            onFavoriteClick
            onDeletedClick
            onSearchClick
             */
                CustomImageButton(
                    image = Icons.Filled.Star,
                    onClick = {
                        onFavoriteClick()
                        favorite.value = !favorite.value
                        deleted.value = false
                    },
                    trigger = favorite
                )

                Spacer(
                    modifier = Modifier
                        .width(width = 6.dp)
                )

                CustomImageButton(
                    image = Icons.Filled.Delete,
                    onClick = {
                        onDeletedClick()
                        deleted.value = !deleted.value
                        favorite.value = false
                    },

                    trigger = deleted

                )
                Spacer(
                    modifier = Modifier
                        .width(width = 6.dp)
                )
                CustomImageButton(
                    image = Icons.Filled.Search,
                    onClick = {
                        onSearchClick()
                        search.value = !search.value
                    },
                    trigger = search
                )
            }
        }
    }
}