package com.example.snailpasswordmanager.presentation.core.components

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains


@Preview
@Composable
fun CustomButtonPreview() {
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
    ) {


        val textPassword = remember { mutableStateOf<String>("test") }
        CustomButton(
            value = "test",
            onClick = {}
        )
    }
}
@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    value : String? = null,
    image : ImageVector? = null,
    imageDescription : String = "icon",
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    val colors = ButtonDefaults.buttonColors(backgroundColor = Color(0x00FFFFFF).copy(alpha = 0.07f))
    val contentColor by colors.contentColor(enabled)


    val absoluteElevation = LocalAbsoluteElevation.current + 0.dp

    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .background(
                color = Color(0x00FFFFFF).copy(alpha = 0.07f),
                shape = RoundedCornerShape(5.dp)
            ),
        propagateMinConstraints = true
    ) {
        Row(
            Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ){
            if (image != null)
                Icon(
                    imageVector = image,
                    contentDescription = imageDescription,
                    tint = Color.DarkGray
                )
            Spacer(modifier = Modifier.width(4.dp))
            if (value != null)
                Text(
                    text = value,
                    color = Color.White.copy(alpha = 1f),
                    textAlign = TextAlign.Center,
                    fontFamily = appFontJetBrains,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = appFontJetBrains
                    ),
                    modifier = modifier.fillMaxWidth()
                )

        }
    }
}

@Composable
fun CustomImageButton(
    modifier: Modifier? = null,
    modifierBox: Modifier = Modifier,
    image: ImageVector,
    indication: Indication? =null,
    onClick : () -> Unit,
    trigger : State<Boolean> = remember { mutableStateOf(false) }
) {

    Box(
        modifier = modifierBox
            .background(
                color = if(trigger.value) Color.White.copy(0.3f) else Color.White.copy(0.07f),
                shape = RoundedCornerShape(4.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = indication,
                enabled = true,
                role = Role.Button,
                onClick = onClick
            ) ,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = true
    ){
        Image(
            imageVector = image,
            contentDescription = "Frame 22",
            colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.3f)),
            modifier = modifier?:Modifier.padding(
                vertical = 4.dp,
                horizontal = 8.dp
            )
        )
    }
}