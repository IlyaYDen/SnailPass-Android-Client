package com.example.snailpasswordmanager.presentation.core.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Note
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains

@Preview(showBackground = true)
@Composable
fun NavigationMenuPreview(){

    val open = remember {mutableStateOf(false)}

    val animation by animateFloatAsState(
        if(open.value) 0.8f else 0f
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF323540)),
        contentAlignment = Alignment.CenterStart
    ) {
        Button(
            modifier = Modifier.fillMaxSize(),
            onClick = {
                open.value =!open.value
            },

        )
        {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        }
        NavigationMenu(
            openSize = animation,
            navigationSelected = NavigationSelected.Accounts
        )
    }
}

/*
,
                        fontFamily = appFontJetBrains
 */
enum class NavigationSelected {
    Home,
    Accounts,
    Note
}

@Composable()
fun NavigationMenu(
    homeButton : () -> Unit = {},
    accountsButton : () -> Unit = {},
    notesButton : () -> Unit = {},
    logoutButton : () -> Unit = {},
    openSize : Float,
    navigationSelected : NavigationSelected
) {

    val HomeSelected = remember {mutableStateOf(false)}
    val AccountsSelected = remember {mutableStateOf(false)}
    val NotesSelected = remember {mutableStateOf(false)}

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    Column(
        Modifier.fillMaxHeight().width((screenWidth*openSize).dp)
            .background(
                color = Color(0xff323540)
            ),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(36.0.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(30.0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
            ) {

            Column(
                //Modifier.size(207.0.dp, 60.0.dp),
                verticalArrangement = Arrangement.spacedBy(8.0.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Spacer(modifier = Modifier.height(6.0.dp))
                Row {
                    Text(
                        "Snail",
                        style = LocalTextStyle.current.copy(
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            fontSize = 20.0.sp,
                            fontFamily = appFontJetBrains
                        ),
                        softWrap = false
                    )
                    Text(
                        "Pass",
                        style = LocalTextStyle.current.copy(
                            color = Color(0xFF56A4FF),
                            textAlign = TextAlign.Left,
                            fontSize = 20.0.sp,
                            fontFamily = appFontJetBrains
                        ),
                        softWrap = false
                    )
                }

                Text(
                    "simple password manager",
                    Modifier,
                    style = LocalTextStyle
                        .current
                        .copy(
                            color = Color(1.0f, 1.0f, 1.0f, 1.0f),
                            textAlign = TextAlign.Center,
                            fontSize = 15.0.sp,
                            fontFamily = appFontJetBrains
                        ),
                    softWrap = false
                )

                Spacer(modifier = Modifier.height(6.0.dp))

                Image(
                    painter = painterResource(id = R.drawable.snailpassnewlogo),
                    contentDescription = "Frame 3",
                    modifier = Modifier
                        .height(200.dp).width(200.dp)
                )
            }
/* raw vector Vector should have an export setting */

        }
        Column(

            verticalArrangement = Arrangement.spacedBy(30.0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
            ) {

            //MenuButton(
            //    icon = Icons.Default.Home,
            //    color = if(navigationSelected == NavigationSelected.Home) Color(0xFF56A4FF) else Color.White,
            //    name =  stringResource(id = R.string.menu_home),
            //    onClick = homeButton,
            //    selected = HomeSelected
            //)

            MenuButton(
                icon = Icons.Default.AccountBox,
                color = if(navigationSelected == NavigationSelected.Accounts) Color(0xFF56A4FF) else Color.White,
                name =  stringResource(id = R.string.menu_acounts),
                onClick = accountsButton,
                selected = AccountsSelected
            )
            MenuButton(
                icon = Icons.Filled.Note,
                color = if(navigationSelected == NavigationSelected.Note) Color(0xFF56A4FF) else Color.White,
                name =  stringResource(id = R.string.menu_notes),
                onClick = notesButton,
                selected = NotesSelected
            )

        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            //spacedBy(35.0.dp)
            Spacer(modifier = Modifier.height(25.dp))
            MenuButton(
                icon = Icons.Filled.Logout,
                color = Color(0xFFFFC0C0),
                name =  stringResource(id = R.string.menu_logout
                ),
                onClick = logoutButton
            )

            Spacer(modifier = Modifier.height(25.dp))
            Text(
                "v0.2.0 beta",
                Modifier
                    .wrapContentHeight(Alignment.Top),
                    //.size(72.0.dp, 18.0.dp),
                style = LocalTextStyle.current
                    .copy(
                        color = Color(1.0f, 1.0f, 1.0f, 1.0f),
                        textAlign = TextAlign.Center,
                        fontSize = 14.0.sp,
                        fontFamily = appFontJetBrains
                    ),
                softWrap = false
            )

            Spacer(modifier = Modifier.height(25.dp))


        }
    }
}

@Composable
fun MenuButton(
    color:Color = Color(1.0f, 1.0f, 1.0f, 1.0f),
    name:String,
    icon: ImageVector,
    selected: State<Boolean> = remember {mutableStateOf(false)},
    onClick: () -> Unit
) {

    Row(

        modifier = Modifier
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(25.0.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Image(
            imageVector = icon,
            contentDescription = name,
            modifier = Modifier,
            colorFilter = ColorFilter.tint(color.copy(alpha = if(selected.value) 0.9f else 0.6f)),
        )

        /* raw vector Vector should have an export setting */
        Text(
            name,
            Modifier.wrapContentHeight(Alignment.Top),
            style = LocalTextStyle.current
                .copy(
                    color = color.copy(alpha = if(selected.value) 0.9f else 0.6f),
                    textAlign = TextAlign.Left,
                    fontSize = 20.0.sp,
                    fontFamily = appFontJetBrains
                ),
            softWrap = false
        )


    }
}
