package com.p3l.gah_mobile.screen.main.reservasi

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.p3l.gah_mobile.data.model.reservasi.DataDetailReservasi
import com.p3l.gah_mobile.data.model.reservasi.FKReservasiFasilitasItem
import com.p3l.gah_mobile.data.model.reservasi.FKReservasiTransaksiKamarItem
import com.p3l.gah_mobile.ui.component.CardTransaksi
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.component.InvoiceCard
import com.p3l.gah_mobile.ui.component.SpinnerBlue
import com.p3l.gah_mobile.ui.component.TableCell
import com.p3l.gah_mobile.ui.component.TableHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailReservasiParent(
    viewModel: DetailReservasiViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.detailReservasiState.collectAsState().value
    var openAlertDialog by remember { mutableStateOf(false) }

    Log.d("DetailReservasiParent", "DetailReservasiParent: ${state.response.idPic}")

    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateToProfile,
                title = "Detail Reservasi"
            )
        }
    ) { innerPadding ->
        if(state.response.idReservasi != null){
            DetailReservasiScreen(
                paddingValues = innerPadding,
                detailReservasi = state.response,

            )
        }else{
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                SpinnerBlue()
            }
        }

    }
}

@Composable
fun DetailReservasiScreen(
    paddingValues: PaddingValues,
    detailReservasi : DataDetailReservasi,
) {
    val column1Weight = .3f // 30%
    val column2Weight = .4f // 70%
    val column3Weight = .3f

    val layanan1Weight = .25f
    val layanan2Weight = .25f
    val layanan3Weight = .25f
    val layanan4Weight = .25f
    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        CardTransaksi(
            id = detailReservasi.idReservasi!!,
            idBooking = detailReservasi.idBooking!!,
            tanggalCheckin = detailReservasi.tanggalCheckin!!,
            tanggalCheckout = detailReservasi.tanggalCheckout!!,
            jumlahDewasa = detailReservasi.jumlahDewasa!!,
            jumlahAnak = detailReservasi.jumlahAnak!!,
            permintaanKhusus = detailReservasi.permintaanKhusus.toString(),
            totalPembayaran = detailReservasi.totalPembayaran!!,
            status = detailReservasi.status!!,
            onClick = {},
            navigateToPembayaran = {},
            onOpenModal = {},
        )


        if(detailReservasi.fKReservasiTransaksiKamar.isNotEmpty()) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "List Kamar",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Column(
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text(
                                text = "Total Harga",
                                style = MaterialTheme.typography.labelMedium,
                            )
                            Text(
                                text = "Rp. ${detailReservasi.totalPembayaran}",
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .clip(Shapes().extraSmall)
                            .background(MaterialTheme.colorScheme.surfaceTint)
                    ) {
                        TableHeader(text = "No Kamar", weight = column1Weight)
                        TableHeader(text = "Jenis Kamar", weight = column2Weight)
                        TableHeader(text = "Bed", weight = column3Weight)
                    }
                    TableKamar(
                        column1Weight = column1Weight,
                        column2Weight = column2Weight,
                        column3Weight = column3Weight,
                        detailReservasi.fKReservasiTransaksiKamar,
                    )
                }

            }
        }

        if(detailReservasi.fKReservasiFasilitas.isNotEmpty()){
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "List Layanan",
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Column(
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = "Total Harga",
                                    style = MaterialTheme.typography.labelMedium,
                                )
                                Text(
                                    text = "Rp. ${
                                        detailReservasi.fKReservasiFasilitas.fold(0) { acc, fkReservasiFasilitasItem ->
                                            acc + fkReservasiFasilitasItem.subTotal!!
                                        }
                                    }",
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            Modifier
                                .clip(Shapes().extraSmall)
                                .background(MaterialTheme.colorScheme.surfaceTint)
                        ) {
                            TableHeader(text = "Nama Layanan", weight = layanan1Weight)
                            TableHeader(text = "Jumlah", weight = layanan2Weight)
                            TableHeader(text = "Tarif", weight = layanan3Weight)
                            TableHeader(text = "Subtotal", weight = layanan4Weight)
                        }
                        TableLayanan(
                            column1Weight = layanan1Weight,
                            column2Weight = layanan2Weight,
                            column3Weight = layanan3Weight,
                            column4Weight = layanan4Weight,
                            detailReservasi.fKReservasiFasilitas,
                        )
                    }

                }
            }

        if(detailReservasi.fKReservasiInvoice != null){
            InvoiceCard(
                idInvoice = detailReservasi.idInvoice!!.toString(),
                totalHarga = detailReservasi.fKReservasiInvoice.totalHarga.toString(),
                totalPajak = detailReservasi.fKReservasiInvoice.totalPajak.toString(),
                totalLayanan = detailReservasi.fKReservasiInvoice.totalLayanan.toString(),
                totalSemua = detailReservasi.fKReservasiInvoice.totalSemua.toString(),
                tanggalPelunasan = detailReservasi.fKReservasiInvoice.tanggalPelunasan.toString(),
                uangJaminan = detailReservasi.uangJaminan.toString(),
                totalDeposit = detailReservasi.totalDeposit.toString(),
            )
        }

    }
}

@Composable
fun TableKamar(
    column1Weight : Float,
    column2Weight : Float,
    column3Weight : Float,
    listKamar : List<FKReservasiTransaksiKamarItem>
) {

    LazyColumn(
        Modifier
            .heightIn(0.dp, 232.dp)
            .clip(Shapes().small)) {

        items(listKamar) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.noKamar.toString(), weight = column1Weight)
                TableCell(text = it.fKTransaksiKamarJenisKamar?.jenisKamar.toString(), weight = column2Weight)
                TableCell(text = it.tipeBed.toString(), weight = column3Weight)
            }
        }
    }
}

@Composable
fun TableLayanan(
    column1Weight : Float,
    column2Weight : Float,
    column3Weight : Float,
    column4Weight : Float,
    listLayanan : List<FKReservasiFasilitasItem>
) {

    LazyColumn(
        Modifier
            .heightIn(0.dp, 232.dp)
            .clip(Shapes().small)) {

        items(listLayanan) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.fKTransaksiFasilitasFasilitas?.namaLayanan.toString(), weight = column1Weight)
                TableCell(text = it.jumlah.toString(), weight = column2Weight)
                TableCell(text = it.fKTransaksiFasilitasFasilitas?.tarif.toString(), weight = column3Weight)
                TableCell(text = it.subTotal.toString(), weight = column4Weight)

            }
        }
    }
}