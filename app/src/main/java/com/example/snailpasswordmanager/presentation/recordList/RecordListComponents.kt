package com.example.snailpasswordmanager.presentation.recordList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainListItem(

) {
    Box(
        Modifier.background(color = Color.Black)
    ) {
        Column(modifier = Modifier
            .background(
                color = Color(0xffd9d9d9).copy(alpha = 0.2f),
                shape = RoundedCornerShape(5)
            )) {
            Text(
                text = "Facebook",
                color = Color(0xffff92c0),
                style = TextStyle(
                    fontSize = 23.sp
                ),
                modifier = Modifier
                    .width(width = 118.dp)
                    .height(height = 37.dp)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(width = 4.dp)
                        .height(height = 52.dp)
                        .background(color = Color(0xffd9d9d9).copy(alpha = 0.1f))
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 7.dp)
                )
                Text(
                    text = "Ilya Kopchigashev",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier
                        .width(width = 187.dp)
                        .height(height = 27.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 7.dp)
                )
                /*Image(
            painter = painterResource(id = R.drawable.frame3),
            contentDescription = "Frame 3",
            colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.3f)))*/
            }
        }

    }
}