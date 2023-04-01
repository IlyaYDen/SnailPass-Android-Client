package com.example.snailpasswordmanager.presentation.noteActivity

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.presentation.core.components.CustomButton
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.core.components.CustomTextFieldFullHeight
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModel
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains
import java.util.*

@Composable
@Preview(showSystemUi = true)
fun NoteScreenPreview(){
    Box(modifier = Modifier.background(color = Color.Black)) {
        //NoteScreen(
        //    "test",
        //    "test",
        //    it.arguments?.getString("favorite"),
        //    it.arguments?.getString("id"),
        //    navController,
        //    noteListViewModel
        //)
    }
}

var NOTE_ENTITY = mutableStateOf(NoteEntity(
    "","","",false,false,"","",""
))

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    //nameString: String ="",
    //valueString: String = "",
    //favorite: Boolean = false,
    id: String? = UUID.randomUUID().toString(),
    nav: NavHostController,
    vm: NoteListViewModel,
    connectivityManager: ConnectivityManager,
    new: Boolean = false
    ){


    //val note = vm.note

    val warning = remember { mutableStateOf("") }
    val warningNote = remember { mutableStateOf("") }
    val empty_error = stringResource(id = R.string.empty_error)


    val fav = remember { mutableStateOf(NOTE_ENTITY.value.is_favorite) }
    val name = remember {
        mutableStateOf(NOTE_ENTITY.value.name)
    }
    val content = remember { mutableStateOf(NOTE_ENTITY.value.content) }



    val connection = remember { mutableStateOf(false) }

    DisposableEffect(key1 = connectivityManager) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                connection.value = true
            }

            override fun onLost(network: Network) {
                connection.value = false
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        onDispose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }




    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Panel({
                nav.popBackStack()
            }, {
                fav.value = !fav.value
            })

            CustomTextField(
                //singleLine = false,
                value = name,
                onValueChange = {
                    name.value = it
                    if (it.isEmpty()) warning.value = empty_error
                    else warning.value = ""
                },
                warning = warning,
                modifier = Modifier,

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

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextFieldFullHeight(
                singleLine = false,
                value = content,
                onValueChange = {
                    content.value = it
                    if (it.isEmpty()) warningNote.value = empty_error
                    else warningNote.value = ""
                },
                warning = warningNote,
                modifier = Modifier
                    .fillMaxHeight(),

                enteredTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Left,
                ),
                hintTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Left,
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 6.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ){
            Column()
            {

                if(connection.value) {
                    if (!new) {
                        CustomButton(
                            image = Icons.Filled.Delete,
                            onClick = {
                                vm.deleteNote(id.toString())
                                nav.popBackStack()
                            },
                            value = "Delete"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    CustomButton(
                        image = Icons.Filled.Save,
                        onClick = {
                            if (
                                name.value.isEmpty() ||
                                content.value.isEmpty()
                            ) return@CustomButton

                            if (new) {
                                vm.insertNote(
                                    NoteEntity(
                                        id = id.toString(),
                                        name = name.value,
                                        content = content.value,
                                        is_favorite = fav.value,
                                        is_deleted = false,
                                        creation_time = "",
                                        update_time = "",
                                        user_id = ""
                                    )
                                )
                            } else {
                                vm.editNote(
                                    NoteEntity(
                                        id = id.toString(),
                                        name = name.value,
                                        content = content.value,
                                        is_favorite = fav.value,
                                        is_deleted = false,
                                        creation_time = "",
                                        update_time = "",
                                        user_id = ""
                                    )
                                )

                            }
                        },
                        value = stringResource(id = R.string.save)
                    )
                }
            }
        }
    }
}
/*


 */



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
        Panel({},{})
    }
}


@Composable
fun Panel(
    onMenuClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean = false
) {

    val favorite = remember { mutableStateOf(isFavorite) }

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