package com.p3l.gah_mobile.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.p3l.gah_mobile.R
import com.p3l.gah_mobile.ui.component.ButtonLoading
import com.p3l.gah_mobile.ui.component.Spinner
import com.p3l.gah_mobile.ui.theme.danger
import com.p3l.gah_mobile.ui.theme.dangerText
import com.p3l.gah_mobile.ui.theme.green
import com.p3l.gah_mobile.ui.theme.greenIcon
import com.p3l.gah_mobile.ui.theme.orange
import com.p3l.gah_mobile.ui.theme.orangeIcon


@Composable
fun ProfileParent(
    viewModel : MainViewModel,
    navigateToEditProfile: () -> Unit,
    navigateToAuth:  () -> Unit,
    navigateToReservasi : () -> Unit,
    navigateToChangePassword : () -> Unit,
    navigateToAbout: () -> Unit,
){
    val state = viewModel.customerInformationState.collectAsState().value
    val logoutState = viewModel.logoutState.collectAsState().value
    var openAlertDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = logoutState, block ={
        if(logoutState.status){
            navigateToAuth()
        }
    })

    if(state.response.idUser != 0) {
        ProfileScreen(
            nama = state.response.nama,
            email = state.response.email,
            navigateToEditProfile = navigateToEditProfile,
            navigateToReservasi = navigateToReservasi,
            logoutCustomer = {
                openAlertDialog = true
            },
            navigateToChangePassword = navigateToChangePassword
        )
    } else{
        ProfileNoAuth(
            navigateToAuth = navigateToAuth,
            navigateToAbout = navigateToAbout
        )
    }

    when {
        openAlertDialog -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    viewModel.logoutCustomer()
                },
                dialogTitle = "Apakah anda yakin ingin logout?",
                dialogText = "Anda akan logout dari aplikasi",
                icon = Icons.Default.Logout,
                isLoading = logoutState.isLoading
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    isLoading: Boolean
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(
                text = dialogTitle,
                style = MaterialTheme.typography.displayMedium
            )
        },
        text = {
            Text(text = dialogText,
                style = MaterialTheme.typography.titleLarge)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                if(isLoading){
                    Spinner()
                }else{
                    Text("Confirm")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun ProfileNoAuth(
    navigateToAuth: () -> Unit,
    navigateToAbout: () -> Unit
){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_login))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(400.dp),
            composition = composition,
        )
        Text(
            text = "Anda belum login",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonLoading(
            isLoading = false,
            onClick = navigateToAuth,
            text = "Log in"
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick =navigateToAbout,
        ){
            Text(
                text = "About Grand Atma Hotel",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ProfileScreen(
    nama  : String,
    email : String,
    navigateToEditProfile : () -> Unit,
    navigateToReservasi : () -> Unit,
    logoutCustomer : () -> Unit,
    navigateToChangePassword : () -> Unit,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceTint
                )
                .statusBarsPadding()
                .padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = nama,
                style =  MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Text(
                text = email,
                style =  MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Account Overview",
                style =  MaterialTheme.typography.labelLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes().large)
                    .clickable { navigateToEditProfile() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    Modifier
                        .clip(Shapes().large)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Edit Profile",
                    style =  MaterialTheme.typography.bodySmall,
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes().large)
                    .clickable { navigateToReservasi() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    Modifier
                        .clip(Shapes().large)
                        .background(green)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Outlined.MonetizationOn,
                        contentDescription = null,
                        tint = greenIcon
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Histori Transaksi",
                    style =  MaterialTheme.typography.bodySmall,
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes().large)
                    .clickable {navigateToChangePassword() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    Modifier
                        .clip(Shapes().large)
                        .background(orange)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null,
                        tint = orangeIcon
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Change Password",
                    style =  MaterialTheme.typography.bodySmall,
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes().large)
                    .clickable { logoutCustomer() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    Modifier
                        .clip(Shapes().large)
                        .background(danger)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Outlined.Logout,
                        contentDescription = null,
                        tint = dangerText
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Logout",
                    style =  MaterialTheme.typography.bodySmall,
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant
                )
            }

        }
    }
}

