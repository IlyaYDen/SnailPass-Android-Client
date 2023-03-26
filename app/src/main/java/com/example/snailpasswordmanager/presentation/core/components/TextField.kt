package com.example.snailpasswordmanager.presentation.core.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains


@Preview
@Composable
fun CustomTextFieldPreview(){
    val text = remember() { mutableStateOf<String>("") }
    val warning = "test"
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Black)) {
        CustomTextField(
            value = text,hint = "E-mail",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                text.value = it
            },
            //warning = warning

        )
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    //BasicTexModifier : Modifier = Modifier,
    value: State<String>,
    hint: String = "",
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    //interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    imageResource: ImageVector? = null,
    warning: State<String> = remember { mutableStateOf("") },
    visualTransformation : VisualTransformation = VisualTransformation.None,

    enteredTextStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
        fontFamily = appFontJetBrains,
        color = Color.White,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Left,
    ),
    hintTextStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
        fontFamily = appFontJetBrains,
        color = Color.White,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Left,
    ),
) {
    val isFocusedState = remember { mutableStateOf(false) }
    val isWarningClicked = remember { mutableStateOf(false) }

    val warningBorder by animateFloatAsState(
        if(warning.value=="") 0f else 0.6f
    )
    val selectedBorder by animateFloatAsState(
        if(!isFocusedState.value) 0f else 0.6f
    )
    onValueChange.also {
        isWarningClicked.value = false
    }
    var t = TextStyle(
        fontSize = enteredTextStyle.fontSize,
        fontWeight = enteredTextStyle.fontWeight,
        fontFamily = appFontJetBrains,
        color = enteredTextStyle.color.copy(alpha = 0.7f),
        fontStyle = FontStyle.Italic,
        textAlign = enteredTextStyle.textAlign
    )
    Box(modifier = modifier
        .fillMaxWidth()) {
        BasicTextField(
            textStyle = if(visualTransformation == PasswordVisualTransformation()) t else enteredTextStyle,
            visualTransformation = visualTransformation,
            value = value.value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            enabled = enabled,
            cursorBrush = Brush.verticalGradient(colors = listOf(Color(0xFFDDDDDD).copy(alpha = 0.4f), Color(0xFFCCCCCC).copy(alpha = 0.3f))),
            //cursorBrush = SolidColor(colors.cursorColor(isError).value),


            modifier = modifier
               .fillMaxWidth()
                .onFocusChanged {
                    isFocusedState.value = it.isFocused
                    if (it.isFocused) {
                        isWarningClicked.value = false
                    }
                },

            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                       .fillMaxWidth()
                        .background(
                            color = Color(0x00D9D9D9).copy(alpha = 0.03f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            //if(warning.value.isEmpty())
                            BorderStroke(
                                2.dp,
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color.Red.copy(alpha = warningBorder),
                                        Color(0xFFCCCCCC).copy(alpha = 0f)
                                    )
                                )
                            ),
                            RoundedCornerShape(4.dp)
                        )
                        .border(
                            if (isFocusedState.value) BorderStroke(
                                2.dp,
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFCCCCCC).copy(alpha = selectedBorder),
                                        Color(0xFFCCCCCC).copy(alpha = 0f)
                                    )
                                )
                            )
                            else
                                BorderStroke(
                                    0.dp,
                                    color = Color.Transparent
                                ),
                            RoundedCornerShape(4.dp)
                        )//*/

                        .padding(all = 8.dp), // inner padding
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    if(imageResource!=null)
                        Icon(
                            imageVector = imageResource,
                            contentDescription = "Favorite icon",
                            tint = Color.DarkGray
                        )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (value.value.isEmpty()) {
                            Text(
                                text = hint,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = appFontJetBrains,
                                fontStyle = FontStyle.Italic,
                                color = Color.White.copy(alpha = 0.3f),
                                //textAlign = textAlign,
                                style = hintTextStyle,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        val align = enteredTextStyle.textAlign

                        var t : Arrangement.Horizontal = Arrangement.Start
                        if(align!=null) {
                            if (align.equals(TextAlign.Center)) {
                                t = Arrangement.Center
                            } else if (align.equals(TextAlign.Right)) {
                                t = Arrangement.End
                            }else if (align.equals(TextAlign.Left)) {
                                t = Arrangement.Start
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = t
                        ) {
                            innerTextField()
                        }
                    }
                }
            }
        )

        if(warningBorder!=0f) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 2.dp)
                ,
                horizontalArrangement = Arrangement.End
            ) {
                CustomWarningField(
                    166*warningBorder,
                    warningText = warning.value,
                    isWarningClicked = isWarningClicked
                )
            }
        }
    }
}


@Preview
@Composable
fun CustomWarningFieldPreview(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Black)) {
        //CustomWarningField(100f)
    }
}

@Composable
fun CustomWarningField(
    animate:Float,
    isWarningClicked: MutableState<Boolean>,// = mutableStateOf(false),
    warningText:String
){


    val warningBorder by animateIntAsState(
        if(isWarningClicked.value) 50 else 0
    )

    var interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .width((38 + warningBorder).dp)
            .height((38).dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(500.dp)
                )
                .width(((animate / 2.6)+warningBorder).dp).height((animate / 2.6).dp)
                .border(
                    shape = RoundedCornerShape(500.dp),
                    border = BorderStroke(
                        3.dp,
                        Brush.radialGradient(
                            colors = listOf(
                                Color.Red,
                                Color(0xFFCCCCCC).copy(alpha = 0f)
                            ),
                            radius = 80f + animate / 4.5f
                        )
                    )
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ){
                    isWarningClicked.value = !isWarningClicked.value
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                if(isWarningClicked.value && warningText.isNotEmpty()){
                    Text(
                        text = warningText,
                        color = Color.Red.copy(alpha = animate / 100),
                        textAlign = TextAlign.Center,
                        fontFamily = appFontJetBrains,
                        fontSize = 16.sp,
                        softWrap = false
                    )
                } else
                    Text(
                        text = "i",
                        color = Color.Red.copy(alpha = animate / 100),
                        textAlign = TextAlign.Center,
                        fontFamily = appFontJetBrains,
                        fontSize = 16.sp
                    )
            }
        }
    }
}


@Composable
fun CustomTextFieldWidth(
    modifier: Modifier = Modifier,
    value: State<String>,
    hint: String = "",
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    //interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    imageResource: ImageVector? = null,
    warning: State<String> = remember { mutableStateOf("") },
    enteredTextStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
        fontFamily = appFontJetBrains,
        color = Color.White,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Left,
    ),
    hintTextStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
        fontFamily = appFontJetBrains,
        color = Color.White,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Left,
    ),
) {
    val isFocusedState = remember { mutableStateOf(false) }
    val isWarningClicked = remember { mutableStateOf(false) }

    val warningBorder by animateFloatAsState(
        if(warning.value=="") 0f else 0.6f
    )
    val selectedBorder by animateFloatAsState(
        if(!isFocusedState.value) 0f else 0.6f
    )
    onValueChange.also {
        isWarningClicked.value = false
    }

    Box() {
        BasicTextField(
            textStyle = enteredTextStyle,

            value = value.value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            enabled = enabled,
            cursorBrush = Brush.verticalGradient(colors = listOf(Color(0xFFDDDDDD).copy(alpha = 0.4f), Color(0xFFCCCCCC).copy(alpha = 0.3f))),
            //cursorBrush = SolidColor(colors.cursorColor(isError).value),


            modifier = modifier
                //.fillMaxWidth()
                .onFocusChanged {
                    isFocusedState.value = it.isFocused
                    if (it.isFocused) {
                        isWarningClicked.value = false
                    }
                },

            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        //.fillMaxWidth()
                        .background(
                            color = Color(0x00D9D9D9).copy(alpha = 0.03f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            //if(warning.value.isEmpty())
                            BorderStroke(
                                2.dp,
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color.Red.copy(alpha = warningBorder),
                                        Color(0xFFCCCCCC).copy(alpha = 0f)
                                    )
                                )
                            ),
                            RoundedCornerShape(4.dp)
                        )
                        .border(
                            if (isFocusedState.value) BorderStroke(
                                2.dp,
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFCCCCCC).copy(alpha = selectedBorder),
                                        Color(0xFFCCCCCC).copy(alpha = 0f)
                                    )
                                )
                            )
                            else
                                BorderStroke(
                                    0.dp,
                                    color = Color.Transparent
                                ),
                            RoundedCornerShape(4.dp)
                        )//*/

                        .padding(all = 8.dp), // inner padding
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    if(imageResource!=null)
                        Icon(
                            imageVector = imageResource,
                            contentDescription = "Favorite icon",
                            tint = Color.DarkGray
                        )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Box {
                        if (value.value.isEmpty()) {
                            Text(
                                text = hint,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = appFontJetBrains,
                                fontStyle = FontStyle.Italic,
                                color = Color.White.copy(alpha = 0.3f),
                                //textAlign = textAlign,
                                style = hintTextStyle,
                                //modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )

        if(warningBorder!=0f) {
            Row(
                modifier = Modifier
                    //.fillMaxWidth()
                    .padding(all = 2.dp)
                ,
                horizontalArrangement = Arrangement.End
            ) {
                CustomWarningField(
                    166*warningBorder,
                    warningText = warning.value,
                    isWarningClicked = isWarningClicked
                )
            }
        }
    }
}

