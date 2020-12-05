package com.johnturkson.tripwatch.android.ui

import android.widget.Toast
import androidx.annotation.MainThread
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.themes.CulturedWhite
import com.johnturkson.tripwatch.android.ui.themes.FernGreen
import com.johnturkson.tripwatch.android.ui.themes.TeaGreen
import com.johnturkson.tripwatch.android.utils.requestLogIn
import com.johnturkson.tripwatch.common.data.UserData
import com.johnturkson.tripwatch.common.responses.Response
import com.johnturkson.tripwatch.common.responses.Response.*
import com.johnturkson.tripwatch.common.responses.Response.Success.OK.CreateUserResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val loginClicked = mutableStateOf(false)
private val emailValid = mutableStateOf(false)
private val passwordValid = mutableStateOf(false)
private val loginSuccess = mutableStateOf(false)

private val emailTextState =  mutableStateOf(TextFieldValue())
private val passwordTextState =  mutableStateOf(TextFieldValue())

@Composable
fun LaunchScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {
    Column(modifier=Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {

        Image(imageResource(R.drawable.trip_watch))

        Column(modifier=Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {

            EmailTextBox(value = emailTextState.value,
                            onValueChange = { emailTextState.value = it })

            PasswordTextBox(value = passwordTextState.value, onValueChange = { passwordTextState.value = it })

            Text(
                textAlign = TextAlign.Center,
                text = if (!emailValid.value && loginClicked.value) "You have entered an invalid email" else "",
                color = MaterialTheme.colors.error)

            Text(
                textAlign = TextAlign.Center,
                text = if (!passwordValid.value && loginClicked.value) "You have entered an invalid password" else "",
                color = MaterialTheme.colors.error)

            Text(
                textAlign = TextAlign.Center,
                text = if (!loginSuccess.value && passwordValid.value && emailValid.value && loginClicked.value) "There was a problem communicating with the server. Try again later" else "",
                color = MaterialTheme.colors.error)
        }

        LoginButton(appContainer, navigationViewModel::navigateTo)
    }
}

@Composable
private fun EmailTextBox(value : TextFieldValue, onValueChange : (TextFieldValue) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                label = { Text("Email") },
                leadingIcon = { Image(Icons.Filled.Email) },
                isErrorValue = !emailValid.value && loginClicked.value)
}

@Composable
private fun PasswordTextBox(value : TextFieldValue, onValueChange : (TextFieldValue) -> Unit ) {
    TextField(modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text("Password") },
        leadingIcon = { Image(Icons.Filled.Lock) },
        visualTransformation = PasswordVisualTransformation(),
        isErrorValue = !passwordValid.value && loginClicked.value)
}

@Composable
private fun LoginButton(appContainer : AppContainer, navigateTo : (Screen) -> Unit) {
    Button(
        colors = ButtonConstants.defaultButtonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        onClick = {
            loginClicked.value = true
            HandleLogIn(appContainer = appContainer,
                userData = UserData(emailTextState.value.text, "", passwordTextState.value.text),
                navigateTo = navigateTo)
        }) {

            Text("Log In")
        }
}

private fun HandleLogIn(appContainer : AppContainer, userData : UserData, navigateTo : (Screen) -> Unit) {
    emailValid.value = emailTextState.value.text.count() > 0 && "@" in emailTextState.value.text
    passwordValid.value = passwordTextState.value.text.count() > 0

    if(emailValid.value && passwordValid.value) {
        val response = runBlocking {
            requestLogIn(userData)
        }

        when(response) {
            is CreateUserResponse -> {
                loginSuccess.value = true
                appContainer.userData = response.user
                navigateTo(Screen.Home)
            }
        }
    }
}