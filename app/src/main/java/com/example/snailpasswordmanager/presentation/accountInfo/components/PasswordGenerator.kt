package com.example.snailpasswordmanager.presentation.accountInfo.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.presentation.core.components.CustomButton
import com.example.snailpasswordmanager.presentation.core.components.CustomTextField
import com.example.snailpasswordmanager.presentation.core.components.CustomTextFieldWidth
import com.example.snailpasswordmanager.presentation.theme.appFontJetBrains
import com.example.snailpasswordmanager.utils.passwordGenerate


@Composable()
fun TextFieldWithGenerator(
    value: MutableState<String> = remember { mutableStateOf("") },
    onValueChange: (String) -> Unit,
    hint: String,
    enteredTextStyle: TextStyle,
    hintTextStyle: TextStyle
) {
    val generatorOpen = remember { mutableStateOf(false) }
    val visible = remember { mutableStateOf(false) }
    val number =  remember { mutableStateOf("8") }

    val selectedBorder by animateFloatAsState(
        if(!generatorOpen.value) -90f else 90f
    )
    val openAnimation by animateIntAsState(
        if(!generatorOpen.value) 0 else 135
    )
    val eyeAnimation by animateIntAsState(
        if(visible.value) 0 else 30
    )

    Column {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomTextField(
                enteredTextStyle = enteredTextStyle,
                hintTextStyle = hintTextStyle,
                hint = hint,
                value = value,
                onValueChange = {
                    onValueChange(it)
                    value.value = it
                },
                visualTransformation = if(!visible.value) PasswordVisualTransformation() else VisualTransformation.None,
            )
            var interactionSource1 = remember { MutableInteractionSource() }

            Row {
                Spacer(modifier = Modifier.width(8.dp)) // Adjust as needed
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        imageVector = Icons.Default.RemoveRedEye,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color(0xff515151)),
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource1,
                                indication = null,
                                enabled = true,
                                role = Role.Button
                            ) {
                                visible.value = !visible.value
                            }
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 30.dp, height = 4.dp)
                            .rotate(45f)) {
                        Box(
                            modifier = Modifier
                                .size(width = eyeAnimation.dp, height = 4.dp)
                                .background(
                                    color = Color(0xff515151),
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp)) // Adjust as needed

                var interactionSource2 = remember { MutableInteractionSource() }
                Image(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(selectedBorder)
                        .clickable(
                            interactionSource = interactionSource2,
                            indication = null,
                            enabled = true,
                            role = Role.Button
                        ) {
                            generatorOpen.value = !generatorOpen.value
                        },
                    colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.3f))
                )
            }
        }
        //if(generatorOpen.value){
        Spacer(modifier = Modifier.height(8.dp)) // Adjust as needed
            PasswordGenerator(
                number,
                value,
                Modifier.height(openAnimation.dp)
            )
        //}
    }
}

@Composable()
fun PasswordGenerator(
    number : MutableState<String> = remember { mutableStateOf("8") },
    textField : MutableState<String> = remember { mutableStateOf("") },
    modifier : Modifier = Modifier
) {
    //val textField = remember { mutableStateOf("test") }

    val small = remember { mutableStateOf(false) }
    val capital = remember { mutableStateOf(false) }
    val numbers = remember { mutableStateOf(false) }
    val symbols = remember { mutableStateOf(false) }


    Column(
        modifier
            .clip(RoundedCornerShape(5.0.dp))
            .background(
                Color(0.85f, 0.85f, 0.85f, 0.03f),
                RoundedCornerShape(5.0.dp)
            )
            .fillMaxWidth()
            //.height(65.dp)
    ) {
            CustomTextField(
                value = number,
                onValueChange = {
                    number.value = it
                },
            )

        Row(
            Modifier
                .height(42.dp),
            horizontalArrangement = Arrangement.spacedBy(18.0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Spacer(modifier = Modifier.width(11.0.dp))
            //Box(
            //    Modifier
            //        .clip(RoundedCornerShape(3.0.dp))
            //        .size(59.0.dp, 30.0.dp)
            //        .background(Color(1.0f, 1.0f, 1.0f, 0.3f)
            //        )
            //)


            Row(
                Modifier.weight(1f),
                // .size(72.0.dp, 30.0.dp),
                //horizontalArrangement = Arrangement.spacedBy(4.0.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                CustomCheckBox(
                    stringResource(id = R.string.a_z_small),
                    small
                )
            }
            Row(
                Modifier.weight(1f),
                //.size(63.0.dp, 30.0.dp),
                //horizontalArrangement = Arrangement.spacedBy(4.0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CustomCheckBox(
                    stringResource(id = R.string.a_z_capital),
                    capital
                )

            }
            Row(
                Modifier.weight(1f),
                //.size(63.0.dp, 30.0.dp),
                //horizontalArrangement = Arrangement.spacedBy(4.0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CustomCheckBox(
                    stringResource(id = R.string.numbers),
                    numbers
                )

            }
            Row(
                Modifier.weight(2f),
                //.size(112.0.dp, 42.0.dp),
                //horizontalArrangement = Arrangement.spacedBy(4.0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CustomCheckBox(
                    stringResource(id = R.string.Symbols),
                    symbols
                )

            }
            //Spacer(modifier = Modifier.width(4.0.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))
        CustomButton(
            value = stringResource(id = R.string.generatePassword),
            onClick = {
                textField.value = passwordGenerate(
                    length = number.value.toInt(),
                    isLowercase = small.value,
                    isUppercase = capital.value,
                    isDigits = numbers.value,
                    isSpecials = symbols.value
                )
            }
        )
    }

}

@Composable
fun CustomCheckBox(
    name :String,
    checked : MutableState<Boolean> = remember { mutableStateOf(false) }
){
    //val checked = remember { mutableStateOf(false) }

    Row {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(1.5.dp))
                    .size(20.0.dp, 20.0.dp)
                    .background(
                        Color(1.0f, 1.0f, 1.0f, 0.3f)
                    )
                    .clickable {
                        checked.value = !checked.value
                        //onCheckedChange(checked.value)
                    }
            )
                Image(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White.copy(if(checked.value) 0.9f else 0f))
                )
        }
        Spacer(modifier = Modifier.width(4.0.dp))

        Text(
            name,
            Modifier
                .wrapContentHeight(Alignment.Top)
                .clickable {
                    checked.value = !checked.value
                    //onCheckedChange(checked.value)
                },
            //.size(38.0.dp, 21.0.dp),
            style = LocalTextStyle.current.copy(
                color = Color(1.0f, 1.0f, 1.0f, 1.0f),
                textAlign = TextAlign.Left, fontSize = 14.sp,
                fontFamily = appFontJetBrains
            )
        )
    }
}