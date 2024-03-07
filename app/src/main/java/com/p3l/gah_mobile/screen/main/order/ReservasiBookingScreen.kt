package com.p3l.gah_mobile.screen.main.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
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
import com.p3l.gah_mobile.screen.main.CustomerInformationState
import com.p3l.gah_mobile.screen.main.DetailReservasi
import com.p3l.gah_mobile.screen.main.Layanan
import com.p3l.gah_mobile.screen.main.MainViewModel
import com.p3l.gah_mobile.screen.main.Room
import com.p3l.gah_mobile.ui.component.LayananDialog
import com.p3l.gah_mobile.ui.component.ProfileCard
import com.p3l.gah_mobile.ui.component.ReservasiTopBar
import com.p3l.gah_mobile.ui.component.RincianCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiBookingParent(
    viewModel: MainViewModel,
    onBack: () -> Unit,
    navigateToReview : () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val detailReservasi = viewModel.detailReservasi.collectAsState().value
    val rooms = viewModel.roomState.collectAsState().value
    val fasilitas = viewModel.fasilitasState.collectAsState().value
    val permintaanKhusus = viewModel.permintaanKhusus.collectAsState().value
    val layanan = viewModel.layananState.collectAsState().value
    val profile = viewModel.customerInformationState.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(block = {
        viewModel.getFasilitas()
    }, key1 = Unit)

    Scaffold(
        topBar = {
            ReservasiTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = onBack,
                title = "Pemesanan Kamar",
                active = 1
            )
        }
    ) {
        ReservasiBookingScreen(
            rooms = rooms,
            layanan = layanan,
            detailReservasi = detailReservasi,
            profile = profile,
            paddingValues = it,
            permintaanKhusus = permintaanKhusus,
            onChangePermintaan = viewModel::setPermintaanKhusus,
            onOpenModal = {
                showDialog = true
            },
            navigateToReview = navigateToReview
        )
    }

    if (showDialog) {
        LayananDialog(
            onDismissDialog= {
                showDialog = false
            },
            listFasilitas = fasilitas.data,
            quantityReturn ={id->
                viewModel.getCurrentLayananQuantityById(id)
            },
            onProductIncreased = {
                viewModel.increaseLayananQuantity(it)
            },
            onProductDecreased= {
                viewModel.decreaseLayananQuantity(it)
            },
            onAddLayanan = {
                viewModel.addLayanan(it)
            }
        )
    }

}

@Composable
fun ReservasiBookingScreen(
    rooms : List<Room>,
    layanan : List<Layanan>,
    detailReservasi : DetailReservasi,
    profile : CustomerInformationState,
    paddingValues: PaddingValues,
    permintaanKhusus : String,
    onChangePermintaan : (String) -> Unit,
    onOpenModal : () -> Unit,
    navigateToReview : () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .padding(paddingValues)
    ) {
        RincianCard(
            checkin = detailReservasi.tanggalCheckIn,
            checkout = detailReservasi.tanggalCheckOut,
            dewasa = detailReservasi.dewasa.toString(),
            anak = detailReservasi.anak.toString(),
            rooms = rooms,
            layanan = layanan,
        )
        ProfileCard(
            nama = profile.response.nama,
            email = profile.response.email,
            noTelepon = profile.response.noTelepon,
            noIdentitas = profile.response.noIdentitas,
            alamat = profile.response.alamat,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Text(
                text = "Layanan Tambahan",
                style = MaterialTheme.typography.titleLarge,
            )
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onOpenModal()
                    },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text(
                        text = "Tambah Layanan",
                    )
                    Icon(
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp),
                        imageVector = Icons.Outlined.Add,
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Permintaan Khusus",
                style = MaterialTheme.typography.titleLarge,
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = permintaanKhusus,
                onValueChange = onChangePermintaan
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = navigateToReview
                ,
                shape = Shapes().small,
            ) {
                Text(text = "Review")
            }
        }

    }
}