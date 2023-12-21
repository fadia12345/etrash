package com.dicoding.etrash.ui.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.etrash.ui.theme.AlegreyaSansFontFamily
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PasswordToggleTextField(
    onValueChange: (String) -> Unit = {},
    hint: String,
    value: String,
    textColor: Color = Color.Black,
    errorMessage: String? = null,
    keyboardType: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Password
    )
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color(0xFFBEC2C2)
                    )
                )
            },
            textStyle = TextStyle(
                color = textColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFBEC2C2),
                unfocusedIndicatorColor = Color(0xFFBEC2C2)
            ),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardType
        )

        PasswordToggleIcon(
            onToggleClick = { passwordVisible = !passwordVisible },
            isVisible = value.isNotEmpty(),
            isVisibleEnabled = passwordVisible
        )
    }

    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp,
            fontFamily = AlegreyaSansFontFamily
        )
    }
}

@Composable
fun PasswordToggleIcon(
    onToggleClick: () -> Unit,
    isVisible: Boolean,
    isVisibleEnabled: Boolean
) {
    if (isVisible) {
        Icon(
            imageVector = if (isVisibleEnabled) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            contentDescription = if (isVisibleEnabled) "Hide Password" else "Show Password",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 4.dp)
                .clickable { onToggleClick() }
        )
    }
}