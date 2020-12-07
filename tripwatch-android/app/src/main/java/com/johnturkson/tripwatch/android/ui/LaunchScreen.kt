package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.utils.*
import com.johnturkson.tripwatch.common.responses.Response.ClientError
import com.johnturkson.tripwatch.common.responses.Response.Success
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private val loginClicked = mutableStateOf(false)
private val emailValid = mutableStateOf(false)
private val passwordValid = mutableStateOf(false)
private val loginSuccess = mutableStateOf(false)
private val errorString = mutableStateOf("")

private val emailTextState =  mutableStateOf(TextFieldValue())
private val passwordTextState =  mutableStateOf(TextFieldValue())

@Composable
fun LaunchScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {
    Column(modifier=Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {

        Image(imageResource(R.drawable.trip_watch))

        Column(modifier=Modifier.fillMaxWidth(), horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {

            EmailTextBox(value = emailTextState.value,
                            onValueChange = { emailTextState.value = it })

            PasswordTextBox(value = passwordTextState.value, onValueChange = { passwordTextState.value = it })

            Text(
                textAlign = TextAlign.Center,
                text = errorString.value,
                color = MaterialTheme.colors.error)

            LoginButton(appContainer, navigationViewModel::navigateTo)
        }

    }
}

@Composable
private fun EmailTextBox(value : TextFieldValue, onValueChange : (TextFieldValue) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                label = { Text("Email") },
                leadingIcon = { Image(Icons.Filled.Email) },
                errorColor = MaterialTheme.colors.error,
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
            backgroundColor = MaterialTheme.colors.primary
        ),
        onClick = {
            handleLogIn(
                appContainer = appContainer,
                emailTextState.value.text,
                passwordTextState.value.text,
                navigateTo = navigateTo
            )
        }) {

        Text("Log In")
    }
}

private fun handleLogIn(appContainer : AppContainer, email : String, password : String, navigateTo : (Screen) -> Unit) {
    emailValid.value = emailTextState.value.text.count() > 0 && "@" in emailTextState.value.text
    passwordValid.value = passwordTextState.value.text.count() > 0

    if(emailValid.value && passwordValid.value) {
         GlobalScope.launch {
            val response = createAccount(email, password)
             loginClicked.value = true

             when(response) {
                 is Success.OK.CreateUserResponse -> {
                     loginSuccess.value = true
                     navigateTo(Screen.Home)
                 }

                 is ClientError.BadRequest -> errorString.value = LOGIN_AUTH_ERROR
                 else -> errorString.value = LOGIN_SERVER_ERROR
             }
        }
    }
    else {
        if(emailValid.value || passwordValid.value) {
            errorString.value = if(!emailValid.value) LOGIN_USERNAME_ERROR else ""
            errorString.value = if(!passwordValid.value) LOGIN_PASSWORD_ERROR else ""
        }
        else {
            errorString.value = LOGIN_AUTH_ERROR
        }
    }
}