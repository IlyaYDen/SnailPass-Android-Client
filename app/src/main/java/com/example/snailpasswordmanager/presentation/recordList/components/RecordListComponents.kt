package com.example.snailpasswordmanager.presentation.recordList.components

import android.graphics.ColorSpace
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.presentation.Screen
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoViewModel
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.recordList.RecordListViewModel
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains
import com.example.snailpasswordmanager.utils.ColorPicker

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun RecordListItemPreview(){

    val t =
        mutableListOf(
            RecordEntity(isdeleted = false, name = "test", login = "test", isfavorite = false),
            RecordEntity(isdeleted = false, name = "test", login = "test", isfavorite = true ),
            RecordEntity(isdeleted = false, name = "test", login = "test", isfavorite = true),
            RecordEntity(isdeleted = false, name = "test", login = "test", isfavorite = false),
            RecordEntity(isdeleted = false, name = "test2", login = "test", isfavorite = true),
            RecordEntity(isdeleted = false, name = "test2", login = "test", isfavorite = false),
            RecordEntity(isdeleted = false, name = "test2", login = "test", isfavorite = true),
            RecordEntity(isdeleted = false, name = "test3", login = "test", isfavorite = false),

            )

    val map = mutableMapOf<String, MutableList<RecordEntity>>()

    t.forEach {
        if(map[it.name] == null) {
            map[it.name] = mutableListOf()
        }
        map[it.name]?.add(it)
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
            SearchPanel({}, {}, {}, {}, remember { mutableStateOf("") },"")
            LazyColumn {
                items(map.keys.size) {
                    map[map.keys.elementAt(it)]?.let { recordList ->
                        //ServiceListItem(
                        //    name = map.keys.elementAt(it),
                        //    recordEntity = recordList,
                        //    null,
                        //    navController
                        //)
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(Alignment.BottomEnd) // Add this line
        ) {
            CustomImageButton(
                image = Icons.Outlined.Add,
                modifier = Modifier.padding(
                    vertical = 0.dp,
                    horizontal = 4.dp
                ),
                onClick = { /*TODO*/
                })

            Spacer(
                modifier = Modifier
                    .width(width = 8.dp)
            )
            CustomImageButton(
                image = Icons.Outlined.Refresh,
                modifier = Modifier.padding(
                    vertical = 0.dp,
                    horizontal = 4.dp
                ),
                onClick = { /*TODO*/
                })
        }
    }
}

lateinit var viewModel: RecordListViewModel
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ServiceListItem(
    name: String,
    recordEntity: List<RecordEntity>,
    vm: RecordListViewModel?,
    recordInfoViewModel: AccountInfoViewModel,
    navController: NavController
) {
    if (vm != null) {
        viewModel = vm
    }
    var color = ColorPicker.rotate(45,45, name.hashCode(),50);
    var radbg = 122;
    var colorbg = ColorPicker.rotate(45,45, -name.hashCode(),radbg);
    Box(
        modifier = Modifier
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
            .background(brush = Brush.horizontalGradient(
                    0f to Color(0xffd9d9d9).copy(0.03f),
                    1f to Color((colorbg[0].toInt()+radbg),(colorbg[1].toInt()+radbg),(colorbg[2].toInt()+radbg),255).copy(0.03f),
                    startX = 0f,
                    tileMode = TileMode.Clamp
                ),
                shape = RoundedCornerShape(5.dp)
            )
    ) {//(colorbg[0].toInt()+radbg),(colorbg[1].toInt()+radbg),(colorbg[2].toInt()+radbg),255

        Column {
            Text(
                text = name,
                color = Color(255-(color[0].toInt()+125),255-(color[1].toInt()+125),255-(color[2].toInt()+125),255),
                style = TextStyle(
                    fontSize = 23.sp,
                    fontFamily = appFontJetBrains
                ),
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 4.dp)
                //.width(width = 118.dp)
                //.height(height = 37.dp)
            )
            if (recordEntity.size == 1) {
                Box {
                    val verticalPadding = 50
                    Box(
                        modifier = Modifier
                            .width(width = 4.dp)
                            .height(height = verticalPadding.dp)
                            .background(
                                color = Color(0xffd9d9d9).copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = verticalPadding.dp),
                        verticalArrangement = Arrangement.Center,
                        userScrollEnabled = false
                    ) {
                        items(recordEntity.size) {
                            //val t = remember {mutableStateOf()}
                            val t = recordEntity.elementAt(it)

                            RecordListItem(
                                t,
                                navController,
                                recordInfoViewModel
                            )
                        }
                        item {
                            Box(modifier = Modifier.height(40.dp))
                        }
                    }
                }
            } else {
                Box {
                    val verticalPadding = 30
                    Box(
                        modifier = Modifier
                            .width(width = 4.dp)
                            .height(height = ((verticalPadding + 2) * recordEntity.size).dp)
                            .background(
                                color = Color(0xffd9d9d9).copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        recordEntity.forEach {


                            RecordListItem(
                                it,
                                navController,
                                recordInfoViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecordListItem(
    recordEntity: RecordEntity,
    navController: NavController,
    recordInfoViewModel: AccountInfoViewModel,
    //favorite: State<Boolean>,
    //deleted: State<Boolean>

){
    val interactionSource = MutableInteractionSource()
    val favorite = remember { mutableStateOf(recordEntity.isfavorite) }
    val deleted = remember { mutableStateOf(recordEntity.isdeleted) }
    deleted.value = recordEntity.isdeleted
    favorite.value = recordEntity.isfavorite
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
            .clickable {
                Log.d("test","test1")
                recordInfoViewModel.getAddFields(recordEntity.id)

                Log.d("test","test2")
                navController.navigate(
                    Screen.RecordInfo.withArgs(
                        Pair("id",recordEntity.id.toString()),
                        Pair("service",recordEntity.name),
                        Pair("email",recordEntity.login),
                        Pair("password",recordEntity.encrypted_password),
                        Pair("favorite",recordEntity.isfavorite.toString()),
                ))
            }
    ) {
        Spacer(
            modifier = Modifier
                .width(width = 7.dp))
        Text(
            text = recordEntity.login,
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontFamily = appFontJetBrains
            )
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = com.example.snailpasswordmanager.R.drawable.baseline_star_24),
                contentDescription = "Frame 3",
                colorFilter = ColorFilter.tint(
                    if(favorite.value) Color.Yellow //Color.copy(alpha = 0.3f)
                    else Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ){
                    favorite.value =!favorite.value
//
                    //viewModel.passwordListEdited.value.forEach {
                    //    if(it.id==recordEntity.id) it.isfavorite=!it.isfavorite
                    //}

                    viewModel.editPassword(
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
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .width(width = 10.dp)
            )
            Image(
                painter = painterResource(id = com.example.snailpasswordmanager.R.drawable.baseline_delete_24),
                contentDescription = "Frame 3",
                colorFilter = ColorFilter.tint(
                    if(deleted.value)
                        Color.White.copy(alpha = 0.9f)
                        else Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ){
                    deleted.value =!deleted.value
//
                    //viewModel.passwordListEdited.value.forEach {
                    //    if(it.id==recordEntity.id) it.isdeleted=!it.isdeleted
                    //}


                    viewModel.editPassword(
                        RecordEntity(
                            id = recordEntity.id,
                            login = recordEntity.login,
                            isfavorite = recordEntity.isfavorite,
                            name = recordEntity.name,
                            userId = recordEntity.userId,
                            isdeleted = !recordEntity.isdeleted,
                            editedTime = recordEntity.editedTime,
                            encrypted_password = recordEntity.encrypted_password,
                            creationTime = recordEntity.creationTime
                        )
                    )
                }
            )
        }
    }
}