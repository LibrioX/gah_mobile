package com.p3l.gah_mobile.screen.main.order

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.screen.main.CustomerInformationState
import com.p3l.gah_mobile.screen.main.DetailReservasi
import com.p3l.gah_mobile.screen.main.Layanan
import com.p3l.gah_mobile.screen.main.MainViewModel
import com.p3l.gah_mobile.screen.main.Room
import com.p3l.gah_mobile.ui.component.ButtonLoading
import com.p3l.gah_mobile.ui.component.CardOrder
import com.p3l.gah_mobile.ui.component.ProfileCard
import com.p3l.gah_mobile.ui.component.ReservasiTopBar
import com.p3l.gah_mobile.ui.component.RincianCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiReviewParent(
    viewModel: MainViewModel,
    onBack: () -> Unit,
    navigateToPembayaran : () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val detailReservasi = viewModel.detailReservasi.collectAsState().value
    val rooms = viewModel.roomState.collectAsState().value
    val permintaanKhusus = viewModel.permintaanKhusus.collectAsState().value
    val layanan = viewModel.layananState.collectAsState().value
    val profile = viewModel.customerInformationState.collectAsState().value
    val createReservasiState = viewModel.stateDataReservasi.collectAsState().value

    LaunchedEffect(key1 = createReservasiState.response, block = {
        if(createReservasiState.response != 0){
            navigateToPembayaran()
        }
    })

    Scaffold(
        topBar = {
            ReservasiTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = onBack,
                title = "Pemesanan Kamar",
                active = 2
            )
        }
    ) {
        ReservasiReviewScreen(
            rooms = rooms,
            layanan = layanan,
            detailReservasi = detailReservasi,
            permintaanKhusus = permintaanKhusus,
            profile = profile,
            paddingValues = it,
            createReservasi = viewModel::createReservasi,
            isLoading = createReservasiState.isLoading
        )
    }
}

@Composable
fun ReservasiReviewScreen(
    rooms : List<Room>,
    layanan : List<Layanan>,
    detailReservasi : DetailReservasi,
    permintaanKhusus : String,
    profile : CustomerInformationState,
    isLoading : Boolean,
    createReservasi : () -> Unit,
    paddingValues: PaddingValues,
){
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

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text  = "Rincian Kamar",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text  = "Berikut adalah kamar yang Anda pesan",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .clip(Shapes().small)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                        shape = Shapes().small
                    )
            ) {
                rooms.forEach {
                    CardOrder(
                        image = it.image,
                        title = "${it.quantity} x ${it.jenisKamar}",
                        price = it.totalPrice
                    )
                }
            }

            if(layanan.isNotEmpty()){
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text  = "Rincian Fasilitas",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text  = "Berikut adalah fasilitas yang Anda pesan",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .clip(Shapes().small)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                            shape = Shapes().small
                        )
                ) {
                    layanan.forEach {
                        CardOrder(
                            image = "https://www.lalamove.com/hubfs/Memulai%20Bisnis%20Laundry%20Koin_%20Peluang%20Usaha%20yang%20Menguntungkan.jpg",
                            title = "${it.quantity} x ${it.nama_layanan}",
                            price = it.totalPrice
                        )
                    }
                }
            }

            if(permintaanKhusus.isNotEmpty()){
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text  = "Permintaan Khusus",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text  = "Berikut adalah permintaan khusus yang Anda tulis",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = permintaanKhusus,
                    readOnly = true,
                    enabled = false,
                    onValueChange = {},
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ButtonLoading(
                text = "Pesan Sekarang",
                isLoading = isLoading,
                onClick = createReservasi
            )

        }

    }
}