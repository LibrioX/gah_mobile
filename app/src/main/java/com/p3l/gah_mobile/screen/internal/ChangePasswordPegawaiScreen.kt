package com.p3l.gah_mobile.screen.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.ui.component.ButtonLoading
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.component.ToastMaker
import com.p3l.gah_mobile.ui.theme.iconColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordPegawaiParent(
    viewModel: HomeInternalViewModel,
    navigateToProfile: () -> Unit,
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.changePasswordState.collectAsState().value


    if(state.status){
        ToastMaker(message =  state.response)
    }else{
        if(!state.status && state.response.isNotEmpty())
            ToastMaker(message = state.response)
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateToProfile,
                title = "Change Password"
            )
        }
    ) { innerPadding ->

        ChangePasswordPegawaiScreen(
            paddingValues = innerPadding,
            changePassword = { password, newPassword ->
                viewModel.changePasswordPegawai(
                    password,
                    newPassword
                )
            },
            isLoading = state.isLoading
        )

    }
}

@Composable
fun ChangePasswordPegawaiScreen(
    paddingValues: PaddingValues,
    changePassword: (String, String) -> Unit,
    isLoading: Boolean
) {
    var password by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Outlined.Lock, contentDescription = null, tint = iconColor)
                },
                label = {
                    Text(text = "Password Lama", style = MaterialTheme.typography.bodySmall)
                },
                onValueChange = {
                    password = it
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = newPassword,
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Outlined.LockOpen, contentDescription = null, tint = iconColor)
                },
                label = {
                    Text(text = "Password Baru", style = MaterialTheme.typography.bodySmall)
                },
                onValueChange = {
                    newPassword = it
                },
            )
        }
        ButtonLoading(
            isLoading = isLoading,
            onClick = {
                changePassword(password, newPassword)
            },
            text = "Simpan",
        )
    }
}