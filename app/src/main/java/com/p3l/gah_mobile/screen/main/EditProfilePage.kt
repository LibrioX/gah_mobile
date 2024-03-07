package com.p3l.gah_mobile.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.main.DataAkunCustomer
import com.p3l.gah_mobile.ui.component.ButtonLoading
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.theme.iconColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileParent(
    viewModel: MainViewModel,
    navigateToProfile: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.customerInformationState.collectAsState().value


    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateToProfile,
                title = "Edit Profile"
            )
        }
    ) {

            EditProfilePage(
                paddingValues = it,
                akunCust = state.response,
                isLoading = state.isLoading,
                onClickUpdate = { email, nama, noIdentitas, noTelepon, alamat ->
                    viewModel.updateCustomer(
                        id = state.response?.idUser.toString(),
                        email = email,
                        nama = nama,
                        noIdentitas = noIdentitas,
                        noTelepon = noTelepon,
                        alamat = alamat
                    )
                }
            )

    }
}

@Composable
fun EditProfilePage(
    paddingValues: PaddingValues,
    akunCust : DataAkunCustomer,
    isLoading : Boolean,
    onClickUpdate: (
        String,
        String,
        String,
        String,
        String,
    ) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var noIdentitas by remember { mutableStateOf("") }
    var noTelepon by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    LaunchedEffect(key1 = akunCust, block = {
        if(akunCust != null){
            email = akunCust.email
            nama = akunCust.nama
            noIdentitas =akunCust.noIdentitas
            noTelepon = akunCust.noTelepon
            alamat = akunCust.alamat
        }
    })

    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Edit Mode",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }

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
            },
            readOnly = !checked
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
            },
            readOnly = !checked
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
            },
            readOnly = !checked
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
            },
            readOnly = !checked
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
            },
            readOnly = !checked
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonLoading(
            isLoading = isLoading,
            onClick = {
                onClickUpdate(
                    email,
                    nama,
                    noIdentitas,
                    noTelepon,
                    alamat,
                )
            },
            text = "Simpan",
            enabled = checked,
        )
    }
}
