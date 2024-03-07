package com.p3l.gah_mobile.screen.auth

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.p3l.gah_mobile.ui.component.Spinner
import com.p3l.gah_mobile.ui.component.ToastMaker
import com.p3l.gah_mobile.ui.theme.iconColor


@Composable
fun RegisterParent(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {
    val state = viewModel.registerState.collectAsState().value

    LaunchedEffect(key1 = state.response, block = {
        if (state.response != null) {
            navigateToLogin()
        }
    })

    if(state.response != null) ToastMaker(message = "Register Success")

    RegisterPage(
        isLoading = state.isLoading,
        error = state.error,
        navigateToLogin = navigateToLogin,
        registerCustomer = {
            nama,email,noIdentitas,noTelepon,alamat,username,password ->
            viewModel.registerCustomer(nama,email,noIdentitas,noTelepon,alamat,username,password)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    isLoading: Boolean,
    error : String,
    navigateToLogin: () -> Unit,
    registerCustomer: (String, String, String, String, String, String, String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var noIdentitas by remember { mutableStateOf("") }
    var noTelepon by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var passwordHidden by remember { mutableStateOf(true) }

    if(error.isNotEmpty()) ToastMaker(message = error)

    Column(
        modifier = modifier
            .verticalScroll(
                rememberScrollState()
            )
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Register",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Welcome Back! Please Sign Up to get great experience",
            style = MaterialTheme.typography.bodySmall
        )
        Divider(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 24.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.People, contentDescription = null, tint = iconColor)
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
            value = email,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Email, contentDescription = null, tint = iconColor)
            },
            label = {
                Text(text = "Email", style = MaterialTheme.typography.bodySmall)
            },
            onValueChange = {
                email = it
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
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nama,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.AccountCircle, contentDescription = null, tint = iconColor)
            },
            label = {
                Text(text = "Nama", style = MaterialTheme.typography.bodySmall)
            },
            onValueChange = {
                nama = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noIdentitas,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Numbers, contentDescription = null, tint = iconColor)
            },
            label = {
                Text(text = "No Identitas", style = MaterialTheme.typography.bodySmall)
            },
            onValueChange = {
                noIdentitas = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noTelepon,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.PhoneAndroid, contentDescription = null, tint = iconColor)
            },
            label = {
                Text(text = "No Telepon", style = MaterialTheme.typography.bodySmall)
            },
            onValueChange = {
                noTelepon = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = alamat,
            leadingIcon = {
                Icon(Icons.Filled.Streetview, contentDescription = null, tint = iconColor)
            },
            label = {
                Text(text = "Alamat", style = MaterialTheme.typography.bodySmall)
            },
            onValueChange = {
                alamat = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                registerCustomer(
                    nama,
                    email,
                    noIdentitas,
                    noTelepon,
                    alamat,
                    username,
                    password
                )
            },
            shape = Shapes().small,
        ) {
            if(isLoading){
                Spinner()
            }else {
                Text(text = "Sign Up")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Have an account?",
                style = MaterialTheme.typography.bodySmall,
            )
            TextButton(onClick = navigateToLogin) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }


    }
}