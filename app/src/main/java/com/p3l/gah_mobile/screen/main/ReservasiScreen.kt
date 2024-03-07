package com.p3l.gah_mobile.screen.main


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.main.FkCustomerReservasiItem
import com.p3l.gah_mobile.ui.component.CardTransaksi
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.theme.iconColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiParent(
    viewModel: MainViewModel,
    navigateToProfile: () -> Unit,
    navigateToDetailReservasi: (Int) -> Unit,
    navigateToPembayaran: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.customerInformationState.collectAsState().value
    val idModal = viewModel.idModalState.collectAsState().value
    val listReservasi = viewModel.listReservasi.collectAsState().value
    var filter by remember {
        mutableStateOf("")
    }
    var openAlertDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = filter, block = {
        viewModel.filterTransaksi(filter)
    })

    if(openAlertDialog){
        AlertDialogExample(
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = {
                             viewModel.changeStatusReservasi("BATAL", idModal)
            },
            dialogTitle = "Pembatalan",
            dialogText = "Ingin Memabatalkan Reservasi ?",
            icon = Icons.Filled.Check,
            isLoading =false
        )
    }


    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateToProfile,
                title = "Histori Reservasi"
            )
        }
    ) {
        ReservasiScreen(
            listReservasi = listReservasi,
            paddingValues = it,
            navigateToDetailReservasi = navigateToDetailReservasi,
            navigateToPembayaran = {
                viewModel.setIdReservasi(it)
                navigateToPembayaran()
            },
            filter = filter,
            setFilter = {
                filter = it
            },
            onOpenModal = {
                openAlertDialog = true
                viewModel.setIdModal(it)
            }
        )
    }
}

@Composable
fun ReservasiScreen(
    listReservasi : List<FkCustomerReservasiItem>,
    paddingValues: PaddingValues,
    navigateToDetailReservasi: (Int) -> Unit,
    navigateToPembayaran: (Int) -> Unit,
    filter : String ,
    setFilter : (String) -> Unit,
    onOpenModal : (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = filter,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = null, tint = iconColor)
            },
            label = {
                Text(text = "Search by id, status", style = MaterialTheme.typography.bodySmall)
            },
            onValueChange = {
                setFilter(it)
            }
        )

        LazyColumn(

        ) {
            items(
                listReservasi,
                key = { it.idReservasi }
            ) { reservasi ->
                CardTransaksi(
                    id = reservasi.idReservasi,
                    idBooking = reservasi.idBooking,
                    tanggalCheckin = reservasi.tanggalCheckin,
                    tanggalCheckout = reservasi.tanggalCheckout,
                    jumlahDewasa = reservasi.jumlahDewasa,
                    jumlahAnak = reservasi.jumlahAnak,
                    permintaanKhusus = "-",
                    totalPembayaran = reservasi.totalPembayaran,
                    status = reservasi.status,
                    onClick = { navigateToDetailReservasi(reservasi.idReservasi) },
                    navigateToPembayaran = navigateToPembayaran,
                    onOpenModal = onOpenModal
                )
            }
        }

    }
}