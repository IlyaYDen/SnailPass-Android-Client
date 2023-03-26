package com.example.snailpasswordmanager.presentation.recordList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Update
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.snailpasswordmanager.presentation.core.components.CustomImageButton

@Composable
//@Preview
fun InteractionPanelPreview() {

}
@Composable
@Preview(showSystemUi = true)
fun CreatePreview(){
        Surface(
            //verticalAlignment = Alignment.Bottom,
            //horizontalArrangement = Arrangement.End,
            modifier = Modifier
                //.background(
                //    brush = Brush.linearGradient(
                //        0f to Color(0xff202327),
                //        1f to Color(0xff26292d),
                //        start = Offset.Zero,
                //        end = Offset.Infinite
                //    )
                //)
                .padding(all = 16.dp),
            contentColor = Color.Transparent
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