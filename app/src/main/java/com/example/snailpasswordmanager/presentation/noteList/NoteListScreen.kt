package com.example.snailpasswordmanager.presentation.noteActivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.presentation.Screen
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModel
import com.example.snailpasswordmanager.presentation.recordList.ListFilter
import com.example.snailpasswordmanager.presentation.recordList.components.SearchPanel
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains
import java.util.*

@Preview
@Composable
fun NoteListScreenPreview() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Blue))
    {
        //NoteListScreen(navController, moteViewModel, context, connectivityManager)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteListScreen(
    navController: NavHostController,
    noteListViewModel: NoteListViewModel,
    context: Context,
    connectivityManager: ConnectivityManager,
    open: MutableState<Boolean> = remember {mutableStateOf(false)}
) {

    val notes = remember { noteListViewModel.noteListEdited }
    val connection = remember { mutableStateOf(false) }


    val map = mutableMapOf<String, MutableList<NoteEntity>>()

    val search = remember { mutableStateOf("") }

    val filter = remember { mutableStateOf(ListFilter.ALL) }

    notes.value.forEach {
        //todo smart search

        if(it.name.contains(search.value, true) /*|| it.content.contains(search.value, true)*/) {

            if (filter.value == ListFilter.ALL) {
                if (!it.is_deleted) {
                    if (map[it.name] == null) {
                        map[it.name] = mutableListOf()
                    }
                    map[it.name]?.add(it)
                }
            }
            if (filter.value == ListFilter.FAVORITES) {
                if (it.is_favorite) {

                    if (map[it.name] == null) {
                        map[it.name] = mutableListOf()
                    }
                    map[it.name]?.add(it)
                }
            }
            if (filter.value == ListFilter.ARCHIVE)
                if (it.is_deleted) {

                    if (map[it.name] == null) {
                        map[it.name] = mutableListOf()
                    }
                    map[it.name]?.add(it)
                }
            if (map[it.name]?.isEmpty() == true && map[it.name] == null)
                map.remove(it.name)
            //if(filter == ListFilter.ALL) {map[it.name]?.add(it)}
        }
    }



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


    Box(
        Modifier.fillMaxSize()
    ) {
        Column {
            SearchPanel(
                onMenuClick = {
                    open.value =!open.value
                },
                onDeletedClick = {
                    if (filter.value != ListFilter.ARCHIVE)
                        filter.value = ListFilter.ARCHIVE
                    else filter.value = ListFilter.ALL
                },
                onFavoriteClick = {
                    if (filter.value != ListFilter.FAVORITES)
                        filter.value = ListFilter.FAVORITES
                    else filter.value = ListFilter.ALL
                },
                onSearchClick = {},
                searchValue = search,
                name = "Notes"
            )
            LazyColumn {
                items(notes.value.size) {
                    NoteComponent(
                        notes.value.get(it),
                        navController,
                        noteListViewModel
                    )
                }
            }
        }

        if(connection.value) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(Alignment.BottomEnd) // Add this line
            ) {
                CustomImageButton(
                    image = Icons.Outlined.Add,
                    onClick = {
                        //navController.navigate(Screen.RecordInfo.route)
                        NOTE_ENTITY = mutableStateOf(NoteEntity(
                            "","","",false,false,"","",""
                        ))

                        navController.navigate(Screen.NoteInfo.route)

                    },
                    indication = LocalIndication.current
                )


                Spacer(
                    modifier = Modifier
                        .width(width = 8.dp)
                )
                CustomImageButton(
                    image = Icons.Outlined.Refresh,
                    onClick = {
                        noteListViewModel.getNotes()
                    },
                    indication = LocalIndication.current
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteComponent(
    noteEntity: NoteEntity,
    navController: NavHostController,
    vm: NoteListViewModel
    ) {

    val favorite = remember { mutableStateOf(noteEntity.is_favorite) }
    val deleted = remember { mutableStateOf(noteEntity.is_deleted) }


    Box(
        modifier = Modifier
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
            .background(
                color = Color(0xffd9d9d9).copy(alpha = 0.03f),
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth()
            .clickable {
                Log.d("test",noteEntity.id)

                NOTE_ENTITY.value = noteEntity

                navController.navigate(
                    Screen.NoteInfo.withArgs(
                        Pair("id",noteEntity.id)
                    )
                    //    .withArgs(
                    //    Pair("id",noteEntity.id),
                    //    Pair("name",noteEntity.name),
                    //    Pair("content",noteEntity.content),
                    //    Pair("favorite",noteEntity.is_favorite.toString())
                    //)
                )
            },
        contentAlignment = Alignment.CenterStart
    ) {


        Row {
                Text(
                    text = noteEntity.name,
                    color = Color(0xffff92c0),
                    style = TextStyle(
                        fontSize = 23.sp,
                        fontFamily = appFontJetBrains
                    ),
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(start = 4.dp)
                    //.width(width = 118.dp)
                    //.height(height = 37.dp)
                )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = "Frame 3",
                colorFilter = ColorFilter.tint(
                    if (favorite.value) Color.Yellow //Color.copy(alpha = 0.3f)
                    else Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier.clickable {
                    favorite.value = !favorite.value
                    val item = noteEntity
                    item.is_favorite = !item.is_favorite

                    vm.editNote(item)
                        /*.editPassword(
                        RecordEntity(
                            id = recordEntity.id,
                            login = recordEntity.login,
                            isfavorite = !recordEntity.isfavorite,//favorite.value,
                            name = recordEntity.name,
                            userId = recordEntity.userId,
                            isdeleted = recordEntity.isdeleted,
                            editedTime = recordEntity.editedTime,
                            encrypted_password = recordEntity.encrypted_password,
                            creationTime = recordEntity.creationTime
                        )
                    )*/
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Frame 3",
                colorFilter = ColorFilter.tint(
                    if(deleted.value)
                        Color.White.copy(alpha = 0.9f)
                    else Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier.clickable {
                    favorite.value = !favorite.value
                    val item = noteEntity
                    item.is_deleted = !item.is_deleted

                    vm.editNote(item)
                }
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
