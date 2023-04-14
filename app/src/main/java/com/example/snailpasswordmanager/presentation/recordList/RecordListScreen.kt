package com.example.snailpasswordmanager.presentation.recordList

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.presentation.Screen
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoViewModel
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.recordList.components.SearchPanel
import com.example.snailpasswordmanager.presentation.recordList.components.ServiceListItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecordListScreen(
    navController: NavController,
    vm: RecordListViewModel,
    recordInfoViewModel: AccountInfoViewModel,
    context: Context,
    connectivityManager: ConnectivityManager,
    open: MutableState<Boolean> = remember { mutableStateOf(false) }
) {


    val t = remember { vm.passwordListEdited }

    val filter = remember { mutableStateOf(ListFilter.ALL) }

    val map = mutableMapOf<String, MutableList<RecordEntity>>()
    val search = remember {mutableStateOf("")}



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


    t.value.forEach {
        //todo smart search

        if(it.name.contains(search.value, true) || it.login.contains(search.value, true)) {

            if (filter.value == ListFilter.ALL) {
                if (!it.isdeleted) {
                    if (map[it.name] == null) {
                        map[it.name] = mutableListOf()
                    }
                    map[it.name]?.add(it)
                }
            }
            if (filter.value == ListFilter.FAVORITES) {
                if (it.isfavorite) {

                    if (map[it.name] == null) {
                        map[it.name] = mutableListOf()
                    }
                    map[it.name]?.add(it)
                }
            }
            if (filter.value == ListFilter.ARCHIVE)
                if (it.isdeleted) {

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


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
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
                name = "Accounts"
            )
            LazyColumn {
                items(map.keys.size) {
                    map[map.keys.elementAt(it)]?.let { recordList ->
                        ServiceListItem(
                            name = map.keys.elementAt(it),
                            recordEntity = recordList,
                            vm,
                            recordInfoViewModel,
                            navController
                        )
                    }
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
                        navController.navigate(Screen.RecordInfo.route)


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
                        vm.getPasswords()
                    },
                    indication = LocalIndication.current
                )
            }
        }
    }
}

enum class ListFilter{
    ALL,
    ARCHIVE,
    FAVORITES
}

