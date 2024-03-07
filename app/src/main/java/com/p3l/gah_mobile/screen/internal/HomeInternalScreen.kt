package com.p3l.gah_mobile.screen.internal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.R
import com.p3l.gah_mobile.screen.main.AlertDialogExample
import com.p3l.gah_mobile.ui.component.CardLaporan
import com.p3l.gah_mobile.ui.theme.secondary
import com.p3l.gah_mobile.ui.theme.success

@Composable
fun HomeInternalParent(
    viewModel: HomeInternalViewModel,
    navigateToChangePasswordPegawai: () -> Unit,
    logoutNavigate : () -> Unit,
    navigateToLaporan1: () -> Unit,
    navigateToLaporan4: () -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val state = viewModel.homeState.collectAsState().value
    val logoutState = viewModel.logoutState.collectAsState().value
    var openAlertDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = logoutState, block = {
        if(logoutState.status){
            logoutNavigate()
        }
    })

    state.response?.let {
        NavigationDrawer(
        username = it.username,
        role = state.response.role,
        drawerState = drawerState,
        navigateToChangePasswordPegawai = navigateToChangePasswordPegawai,
        openDialog = {
            openAlertDialog = true
        },
        ) {
            Scaffold(

                content = { paddingValues ->
                    HomeInternalScreen(
                        paddingValues,
                        navigateToLaporan1 = navigateToLaporan1,
                        navigateToLaporan4 = navigateToLaporan4,
                    )
                }
            )
        }
    }

    when {
        openAlertDialog -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    viewModel.logoutPegawai()
                },
                dialogTitle = "Apakah anda yakin ingin logout?",
                dialogText = "Anda akan logout dari aplikasi",
                icon = Icons.Default.Logout,
                isLoading = logoutState.isLoading
            )
        }
    }
}

@Composable
fun HomeInternalScreen(
    paddingValues: PaddingValues,
    navigateToLaporan1: () -> Unit,
    navigateToLaporan4: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text(
            text = "List Laporan",
            style = MaterialTheme.typography.displayMedium,
        )
        CardLaporan(
            title = "Laporan 1",
            description = "Laporan mengenai customer baru yang mendafatar",
            icon = Icons.Default.People,
            color = secondary,
            onClick = navigateToLaporan1
        )
        CardLaporan(
            title = "Laporan 4",
            description = "Laporan 5 customer dengan pemesanan terbanyak",
            icon = Icons.Default.VerifiedUser,
            color = success,
            onClick = navigateToLaporan4
        )
    }
}


@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    username : String ,
    role : String ,
    openDialog : () -> Unit,
    navigateToChangePasswordPegawai: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "Logo"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = username,
                        style =  MaterialTheme.typography.titleLarge,
                    )

                    Text(
                        text = role,
                        style =  MaterialTheme.typography.titleLarge,
                    )
                }

                NavigationDrawerItem(label = {
                    Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Change Password", color = MaterialTheme.colorScheme.onSurface)
                    }
                }, selected = false, onClick = navigateToChangePasswordPegawai)

                NavigationDrawerItem(label = {
                    Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Log out", color = MaterialTheme.colorScheme.onSurface)
                    }
                }, selected = false, onClick = openDialog)
                }
        },
        content = content
    )

}