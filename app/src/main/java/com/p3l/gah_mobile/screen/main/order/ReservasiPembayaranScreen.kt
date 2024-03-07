package com.p3l.gah_mobile.screen.main.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.reservasi.ResponseTandaTerima
import com.p3l.gah_mobile.screen.main.CustomerInformationState
import com.p3l.gah_mobile.screen.main.MainViewModel
import com.p3l.gah_mobile.ui.component.ButtonLoading
import com.p3l.gah_mobile.ui.component.ProfileCard
import com.p3l.gah_mobile.ui.component.ReservasiTopBar
import com.p3l.gah_mobile.ui.component.RincianCard2
import com.p3l.gah_mobile.ui.component.SpinnerBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiPembayaranParent(
    viewModel: MainViewModel,
    onBack: () -> Unit,
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val tandaTerima = viewModel.tandaTerimaState.collectAsState().value
    val changeStatusState = viewModel.changeStatusReservasiState.collectAsState().value
    val profile = viewModel.customerInformationState.collectAsState().value

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getTandaTerima()
    })

    LaunchedEffect(key1 = changeStatusState, block = {
        if(changeStatusState.response.isNotEmpty()){
            onBack()
        }
    })

    if(tandaTerima.isLoading || tandaTerima.response.data.idBooking.isEmpty()){
        SpinnerBlue()
    }else{
        Scaffold(
            topBar = {
                ReservasiTopBar(
                    scrollBehavior = scrollBehaviour,
                    onMenuClicked = onBack,
                    title = "Pemesanan Kamar",
                    active = 3
                )
            }
        ) {
            ReservasiPembayaranScreen(
                tandaTerima = tandaTerima.response,
                profile = profile,
                paddingValues = it,
                isLoading = changeStatusState.isLoading,
                onChangeStatusReservasi = {
                    viewModel.changeStatusReservasi("SUDAH DIBAYAR")
                }
            )
        }
    }


}

@Composable
fun ReservasiPembayaranScreen(
    tandaTerima: ResponseTandaTerima,
    profile : CustomerInformationState,
    isLoading : Boolean,
    onChangeStatusReservasi: () -> Unit,
    paddingValues: PaddingValues
) {

    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .padding(paddingValues)
    ) {
        RincianCard2(
            idBooking = tandaTerima.data.idBooking,
            checkin = tandaTerima.data.checkin,
            checkout = tandaTerima.data.checkout,
            dewasa = tandaTerima.data.dewasa.toString(),
            anak = tandaTerima.data.anak.toString(),
            rooms = tandaTerima.data.kamars,
            layanan = tandaTerima.data.layanan,
        )
        ProfileCard(
            nama = profile.response.nama,
            email = profile.response.email,
            noTelepon = profile.response.noTelepon,
            noIdentitas = profile.response.noIdentitas,
            alamat = profile.response.alamat,
        )

        ButtonLoading(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Submit dan pesan",
            isLoading = isLoading,
            onClick = onChangeStatusReservasi
        )
    }



}
