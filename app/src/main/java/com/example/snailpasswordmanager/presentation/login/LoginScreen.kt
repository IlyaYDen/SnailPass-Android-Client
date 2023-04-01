package com.example.snailpasswordmanager.presentation.login.components

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.Screen
import com.example.snailpasswordmanager.presentation.core.components.CustomButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains


private lateinit var vm : LoginViewModel
//@Inject
//lateinit var vmFactory: LoginModelFactory
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel,
    context: Context
) {

    val warningEmail = remember { mutableStateOf("") }
    val warningPassword = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val viewEffects = remember { vm.sharedViewEffects }
    LaunchedEffect(viewEffects) {
        viewEffects.collect { (message, mode) ->
            if (mode == LoginMode.ONLINE || mode == LoginMode.OFFLINE) {

                navController.navigate(Screen.RecordList.route)

            } else if (mode == LoginMode.ERROR) {
                loading.value = false
                if (message.isNotBlank()) {
                    warningEmail.value = message
                    warningPassword.value = message
                }
            }
        }
    }
    /*
    colors = listOf(Color(0xff202327), Color(0xff26292d)),
                from = Offset.Zero,
                to = Offset.Infinite,
                tileMode = TileMode.Clamp,
                colorStops = listOf(0f, 0.95f)
     */





    Box(
        modifier = Modifier
            .fillMaxSize()
                //Brush.verticalGradient(
                //    colors = listOf(
                //        Color(0xff202327),
                //        Color(0xff26292d),
                //    )
                //)
                //brush = Brush.linearGradient(
                //    0f to Color(0xff202327),
                //    1f to Color(0xff26292d),
                //    start = Offset.Zero,
                //    end = Offset.Infinite,
                //    tileMode = TileMode.Repeated
                //)
            .padding(horizontal = 13.dp)
            .padding(top = 40.dp)
    )
    {



        Column(
            //verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(start = 100.dp,top = 100.dp, end = 100.dp)) {
                Image(
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = R.drawable.snailpassnewlogo),
                    contentDescription = "padding1"
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(id = R.string.Sign_in_to_continue),
                fontFamily = appFontJetBrains,
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = appFontJetBrains,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light))


            val textEmail = remember { mutableStateOf("") }
            CustomTextField(
                value = textEmail, hint = "E-mail",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    textEmail.value = it
                },
                warning = warningEmail,



                enteredTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center, //left
                ),
                hintTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center, //left
                )
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            val textPassword = remember { mutableStateOf<String>("") }
            CustomTextField(
                visualTransformation = PasswordVisualTransformation(),
                value = textPassword, hint = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    textPassword.value = it
                },
                warning = warningPassword,


                enteredTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center, //left
                ),
                hintTextStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = appFontJetBrains,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center, //left
                )
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            CustomButton(
                value ="Sing in",
                onClick = {
                    warningEmail.value = ""
                    warningPassword.value = ""
                    if(
                        textEmail.value.length>5 &&
                        textPassword.value.length>10) {



                        loading.value = true

                        val userEntity = UserEntity(
                            email = textEmail.value.lowercase(),
                            password = textPassword.value,
                            hint = ""
                        )
                        vm.logInEvent(userEntity)
                    }
                    else{
                        warningEmail.value = context.getString(R.string.login_error)
                        warningPassword.value = context.getString(R.string.login_error)//context.getString(id = R.string.login_error)//"Please enter a valid email and password."
                        //loginText.requestFocus()
                    }



                }
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            CustomButton(
                value = "Sing up",
                onClick = {
                    warningEmail.value = ""
                    warningPassword.value = ""
                    navController.navigate(Screen.Registration.withArgs(
                        Pair("email",textEmail.value),
                        Pair("password",textPassword.value)
                    ))
                }
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            if(loading.value) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }
        }
    }
}






