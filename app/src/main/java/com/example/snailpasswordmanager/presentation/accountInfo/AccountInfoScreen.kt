package com.example.snailpasswordmanager.presentation.accountInfo

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.presentation.Screen
import com.example.snailpasswordmanager.presentation.accountInfo.components.Panel
import com.example.snailpasswordmanager.presentation.accountInfo.components.TextFieldWithGenerator
import com.example.snailpasswordmanager.presentation.core.components.CustomButton
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.core.components.CustomTextFieldWidth
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains
import java.util.*



@Preview
@Composable
fun AccountInfoScreenPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
       // AccountInfoScreen(navController = null, vm = null)
    }
}
/*
0 - no changes
1 - added
2 - deleted
3 - edited
 */

//var additional =  mutableListOf<Pair<RecordAddFieldEntity,Int>>()

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountInfoScreen(
    navController: NavHostController,
    vm: AccountInfoViewModel,
    id: String? = UUID.randomUUID().toString(),
    name: String? = null,
    email: String? = null,
    password: String? = null,
    favorite: Boolean = false,
    new: Boolean = true,
    connectivityManager: ConnectivityManager
) {

    val items = vm.fieldListEdited

    val serviceValue = remember { mutableStateOf(name?:"") }
    val loginValue = remember { mutableStateOf(email?:"") }
    val passwordValue = remember { mutableStateOf(password?:"") }
    val isFavorite = remember(id) { mutableStateOf(favorite) }

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


    val viewEffects = remember { vm.responce }

    LaunchedEffect(viewEffects) {
        viewEffects.collect { bool ->
            Log.d("TEG", "Effect: $bool")
            if(bool){
                //navController.navigate(Screen.RecordList.route) todo

                navController.popBackStack()
                vm.responce.value = false
            }
        }
    }


    Box {
        Column {
            Panel(serviceValue, {
                navController.popBackStack()
                //navController.navigate(Screen.RecordList.route)
            }, {
                isFavorite.value = !isFavorite.value
            },isFavorite.value
            )
            Column(
                modifier = Modifier.padding(all = 16.dp),
            ) {
                //SearchPanel(value,{},{})

                LazyColumn(
                    modifier = Modifier.padding(bottom = (if(!new) 95 else 47).dp)
                ) {
                    item {
                        AccountInfoItem(
                            R.string.login_name,
                            R.string.login_name,
                            loginValue
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        AccountInfoItem(
                            R.string.password,
                            R.string.password,
                            passwordValue,true
                        )
                    }
                    items(items.size) {

                        if(items[it].second != 2) {
                            val name = items[it].first.name
                            val value = items[it].first.value
                            Spacer(modifier = Modifier.height(25.dp))
                            AccountInfoAdditionalItem(
                                name,
                                value,
                                it,
                                vm
                            )
                        }
                    }
                    item {
                        if(connection.value) {
                            Spacer(modifier = Modifier.height(12.dp))
                            CustomImageButton(
                                image = Icons.Filled.Add,
                                onClick = {
                                    vm.addField(UUID.fromString(id))
                                },
                                modifierBox = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

            }
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

                            },
                            value = "Delete"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    CustomButton(
                        image = Icons.Filled.Save,
                        onClick = {
                            if (
                                serviceValue.value.isEmpty() ||
                                passwordValue.value.isEmpty() ||
                                loginValue.value.isEmpty()
                            ) return@CustomButton
                            items.forEach {
                                if (it.first.value.isEmpty() || it.first.name.isEmpty()) return@CustomButton
                            }
                            if (new) {
                                vm.addPassword(
                                    RecordEntity(
                                        id = UUID.fromString(id),
                                        name = serviceValue.value,
                                        login = loginValue.value,
                                        encrypted_password = passwordValue.value,
                                        userId = UUID.randomUUID().toString(),
                                        isfavorite = isFavorite.value,
                                        isdeleted = false,
                                        editedTime = "",
                                        creationTime = ""
                                    )
                                )
                                val list = mutableListOf<RecordAddFieldEntity>()
                                items.forEach {
                                    list.add(it.first)
                                }
                                vm.addFieldsToServer(
                                    list
                                )
                            } else {
                                vm.editPassword(
                                    RecordEntity(
                                        id = UUID.fromString(id),
                                        name = serviceValue.value,
                                        login = loginValue.value,
                                        encrypted_password = passwordValue.value,
                                        userId = "",
                                        isfavorite = isFavorite.value,
                                        isdeleted = false,
                                        editedTime = "",
                                        creationTime = ""
                                    )
                                )
                                val addList = ArrayList<RecordAddFieldEntity>()
                                val editedList = ArrayList<RecordAddFieldEntity>()
                                val deletedList = ArrayList<UUID>()

                                items.forEach {
                                    val n = it.second
                                    val item = it.first
                                    if (n == 1) addList.add(item)
                                    if (n == 2) deletedList.add(item.id)
                                    if (n == 3) editedList.add(item)
                                }
                                if (addList.isNotEmpty()) vm.addFieldsToServer(addList)
                                if (editedList.isNotEmpty()) vm.editFieldsToServer(editedList)
                                if (deletedList.isNotEmpty()) vm.deleteFieldsFromServer(deletedList)

                            }
                        },
                        value = stringResource(id = R.string.save)
                    )
                }
            }
        }
    }

}

@Composable
fun AccountInfoItem(
    name: Int,
    hint: Int,
    value: MutableState<String>,
    password: Boolean = false,
) {

    val warning = remember { mutableStateOf("") }
    val empty_error = stringResource(id = R.string.empty_error)

    Text(
        text = stringResource(id = name),
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = appFontJetBrains,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        color = Color.White.copy(alpha = 0.9f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp)
    )
    if(password) TextFieldWithGenerator(
        value = value,
        onValueChange = {
            value.value = it
            if(it.isEmpty()) warning.value = empty_error
            else warning.value = ""
        },
        hint = stringResource(id = hint),
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
    ) else
    CustomTextField(
        value = value,
        onValueChange = {
            value.value = it
            if(it.isEmpty()) warning.value = empty_error
            else warning.value = ""
        },
        hint = stringResource(id = hint),
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

@Preview
@Composable
fun AccountInfoAdditionalItemPreview(){
    Box(modifier = Modifier.background(color = Color.Black)) {
        //AccountInfoAdditionalItem(0, 0, remember { mutableStateOf("test") })
    }
}
@Composable
fun AccountInfoAdditionalItem(
    nameString: String,
    valueString: String,
    i: Int,
    vm: AccountInfoViewModel
) {
    val empty_error = stringResource(id = R.string.empty_error)
    val warning_name = remember { mutableStateOf("") }
    val warning_value = remember { mutableStateOf("") }

    val name = remember(vm.fieldListEdited[i].first.id) { mutableStateOf(nameString) }
    val value = remember(vm.fieldListEdited[i].first.id) { mutableStateOf(valueString) }
    Column {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(all=4.dp)) {
            Row {
                CustomTextFieldWidth(
                    modifier = Modifier
                        .weight(2f),
                    value = name,
                    onValueChange = {
                        name.value = it
                        if(it.isEmpty()) warning_name.value = empty_error
                        else warning_name.value = ""
                        vm.editField(name.value, value.value,i)
                    },
                    hint = "hint.value",
                    warning = warning_name,
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
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomImageButton(image = Icons.Filled.Delete,
                    modifier = Modifier,
                    modifierBox = Modifier
                        .padding(end = 6.dp),
                    onClick = {
                        vm.deleteField(i)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        CustomTextField(
            value = value,
            onValueChange = {
                value.value = it
                vm.editField(name.value, value.value,i)
                if(it.isEmpty()) warning_value.value = empty_error
                else warning_value.value = ""
            },
            hint = "hint.value",
            warning = warning_value,
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
}
/*

            Text(
                text = stringResource(id = com.example.snailpasswordmanager.R.string.login_name),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = appFontJetBrains,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                color = Color.White.copy(alpha = 0.9f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, bottom = 2.dp)
            )
            CustomTextField(
                value =value,
                onValueChange = {},
                hint = "test",
                textAlign = TextAlign.Center
            )
 */

