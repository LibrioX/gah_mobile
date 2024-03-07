package com.p3l.gah_mobile.screen.internal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.p3l.gah_mobile.R
import com.p3l.gah_mobile.ui.component.ButtonLoading
import com.p3l.gah_mobile.ui.component.ToastMaker
import com.p3l.gah_mobile.ui.theme.iconColor

@Composable
fun LoginInternalParent(
    viewModel: LoginInternalViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {

    val state = viewModel.loginState.collectAsState().value

    if(state.error.isNotEmpty()){
        ToastMaker(message = state.error)
    }

    LaunchedEffect(key1 = state.response, block = {
        if(state.response != null ){
            navigateToHome()
        }
    })

    LoginInternalScreen(
        isLoading = state.isLoading,
        login = { username, password ->
            viewModel.loginInternal(username, password)
        },
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun LoginInternalScreen(
    isLoading : Boolean = false,
    login: (String, String) -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }


    Column(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Log In Pegawai",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "Welcome Back! Please Log in to get great experience",
                style = MaterialTheme.typography.bodySmall
            )
            Divider(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 36.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = null, tint = iconColor)
                },
                label = {
                    Text(text = "Username", style = MaterialTheme.typography.bodySmall)
                },
                onValueChange = {
                    username = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                label = { Text("Enter password", style = MaterialTheme.typography.bodySmall) },
                visualTransformation =
                if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        val visibilityIcon =
                            if (!passwordHidden) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        // Please provide localized description for accessibility services
                        val description = if (passwordHidden) "Show password" else "Hide password"
                        Icon(
                            imageVector = visibilityIcon,
                            contentDescription = description,
                            tint = iconColor
                        )
                    }
                }

            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonLoading(
                isLoading = isLoading,
                onClick = {
                    login(username, password)
                },
                text = "Log In",
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(onClick = navigateToLogin) {
                Text(
                    text = "Log In Customer",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }


    }
}