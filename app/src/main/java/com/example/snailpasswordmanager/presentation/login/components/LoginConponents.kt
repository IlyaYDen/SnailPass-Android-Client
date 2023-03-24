package com.example.snailpasswordmanager.presentation.login.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.    R
import com.example.snailpasswordmanager.presentation.core.Fonts
import com.example.snailpasswordmanager.presentation.core.components.CustomButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField

//class LoginView {
//} todo make password, buttons more hight

//@Preview(device = Devices.DEFAULT)
@Composable
fun LoginScreen() {
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
                fontFamily = Fonts.appFontJetBrains,
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light))


            val textEmail = remember() { mutableStateOf<String>("") }
            val warningEmail = remember() { mutableStateOf(false) }
            CustomTextField(
                value = textEmail, hint = "E-mail",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    textEmail.value = it
                },
                warning = "email",
                isWarning = warningEmail
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            val textPassword = remember() { mutableStateOf<String>("") }
            val warningPassword = remember() { mutableStateOf(false) }
            CustomTextField(
                value = textPassword, hint = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    textPassword.value = it
                },
                warning = "password",
                isWarning = warningPassword
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            CustomButton(
                value ="Sing in",
                onClick = {
                    click.value = !click.value
                    warningEmail.value = click.value
                }
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            CustomButton(
                value = "Sing up",
                onClick = {
                    click.value = !click.value
                    warningPassword.value = click.value
                }
            )
        }
    }
}






