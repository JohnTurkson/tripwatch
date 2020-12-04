package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.ui.themes.FernGreen
import com.johnturkson.tripwatch.android.ui.themes.TeaGreen

@Composable
fun LaunchScreen(navigationViewModel: NavigationViewModel) {
    Column(modifier=Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
        Image(imageResource(R.drawable.trip_watch))

        val emailTextState = remember { mutableStateOf(TextFieldValue()) }
        val passwordTextState = remember { mutableStateOf(TextFieldValue()) }

        Column(modifier=Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            EmailTextBox(value = emailTextState.value,
                onValueChange = { emailTextState.value = it })

            PasswordTextBox(value = passwordTextState.value, onValueChange = { passwordTextState.value = it })
        }

        LoginButton(navigationViewModel::navigateTo)
    }
}

@Composable
fun EmailTextBox(value : TextFieldValue, onValueChange : (TextFieldValue) -> Unit ) {
    TextField(modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White,
                value = value,
                onValueChange = onValueChange,
                label = { Text("Email") },
                leadingIcon = { Image(Icons.Filled.Email) })
}

@Composable
fun PasswordTextBox(value : TextFieldValue, onValueChange : (TextFieldValue) -> Unit ) {
    TextField(modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        value = value,
        onValueChange = onValueChange,
        label = { Text("Password") },
        leadingIcon = { Image(Icons.Filled.Lock) },
        visualTransformation = PasswordVisualTransformation())
}

@Composable
fun LoginButton(navigateTo : (Screen) -> Unit) {
    Button(
        onClick = { navigateTo(Screen.Home) },
        colors = ButtonConstants.defaultButtonColors(backgroundColor = Color.Green)) {
        Text("Log In")
    }
}