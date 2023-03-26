package com.example.snailpasswordmanager.presentation.registration

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.Screen
import com.example.snailpasswordmanager.presentation.core.components.CustomButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegistrationScreen(
    navController: NavController,
    vm: RegistrationViewModel,
    email : String?,
    password : String?
) {

    val viewEffects = remember { vm.boolean }

    val warningEmail = remember() { mutableStateOf("") }
    val loading = remember() { mutableStateOf(false) }
    val user_already_exists = stringResource(R.string.user_already_exists)
    LaunchedEffect(viewEffects) {
        viewEffects.collect {it ->

            if(it.first){
                //todo notification to check email address
                navController.navigate(Screen.Login.route)
            }
            else
                if(it.second!="")
                    if(it.second == "\"Resource Already Exists. User with this email already exists\"")
                        warningEmail.value = user_already_exists
            loading.value = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xff202327),
                    1f to Color(0xff26292d),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .padding(horizontal = 13.dp)
            .padding(top = 40.dp)
    )
    {

        val click = remember { mutableStateOf(false) }


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
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light)
            )


            val textEmail = remember() { mutableStateOf<String>(email ?: "") }
            CustomTextField(
                value = textEmail, hint = "E-mail",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    textEmail.value = it
                    warningEmail.value = ""
                },
                warning = warningEmail,
                //isWarning = warningEmail
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            val textPassword = remember() { mutableStateOf<String>(password ?: "") }
            val warningPassword = remember() { mutableStateOf("") }
            CustomTextField(
                visualTransformation = PasswordVisualTransformation(),
                value = textPassword, hint = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    textPassword.value = it
                },
                //warning = "password",
                warning = warningPassword
            )
            Spacer(modifier = Modifier.height(height = 7.dp))

            val textPasswordRepeat = remember() { mutableStateOf<String>("") }
            val warningPasswordRepeat = remember() { mutableStateOf("") }
            CustomTextField(
                visualTransformation = PasswordVisualTransformation(),
                value = textPasswordRepeat, hint = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    textPasswordRepeat.value = it
                },
                //warning = "passwordRepeat",
                warning = warningPasswordRepeat
            )
            Spacer(modifier = Modifier.height(height = 7.dp))

            val textHint = remember() { mutableStateOf<String>("") }
            //val warningHint = remember() { mutableStateOf(false) }
            CustomTextField(
                value = textHint, hint = "Hint",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    textHint.value = it
                }
            )
            Spacer(modifier = Modifier.height(height = 7.dp))


            CustomButton(
                value ="Sing up",
                onClick = {

                    if(textPassword.value != textPasswordRepeat.value){
                        warningPassword.value = "passwords_dont_match"
                        warningPasswordRepeat.value = "passwords_dont_match"
                        return@CustomButton
                    }
                    vm.registrationEvent(
                        UserEntity(
                            id = UUID.randomUUID(),
                            email = textEmail.value,
                            password = textPassword.value,
                            hint = "",
                            isAdmin = false
                        )
                    )

                }
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            CustomButton(
                value = "Sing in",
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

