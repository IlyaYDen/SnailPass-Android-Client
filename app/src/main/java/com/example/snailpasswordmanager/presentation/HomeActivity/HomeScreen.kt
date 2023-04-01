package com.example.snailpasswordmanager.presentation.HomeActivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains

@Composable
@Preview
fun HomeScreenPreview(){
    Box(modifier = Modifier.background(color = Color.Black)) {
        HomeScreen()
    }
}
@Composable
fun HomeScreen(){
    Column(){
        HomePanel()
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = R.drawable.snailpassnewlogo),
            contentDescription = "Frame 3",
            modifier = Modifier
                .height(200.dp).width(200.dp)
        )


    }
}

@Composable
fun HomePanel() {
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
                imageVector = Icons.Filled.Menu,
                contentDescription = "Vector",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {

                    },
                colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.3f))
            )
        }
    }
}