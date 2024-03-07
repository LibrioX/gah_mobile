package com.p3l.gah_mobile.screen.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.internal.DataLaporan4
import com.p3l.gah_mobile.ui.component.DropdownLaporan
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.component.SpinnerBlue
import com.p3l.gah_mobile.ui.component.TableCell
import com.p3l.gah_mobile.ui.component.TableHeader
import com.p3l.gah_mobile.ui.theme.success
import com.p3l.gah_mobile.ui.theme.successTable
import com.p3l.gah_mobile.ui.theme.successText
import com.p3l.gah_mobile.util.numberFormatRupiah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Laporan4Parent(
    viewModel: HomeInternalViewModel,
    navigateBack: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.laporan4State.collectAsState().value
    var tahun by remember { mutableStateOf("2023") }

    LaunchedEffect(key1 = tahun, block = {
        if(tahun.isEmpty()){
            viewModel.getLaporan4()
        } else {
            viewModel.getLaporan4(tahun.toInt())
        }
    })


    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateBack,
                title = "Laporan 4"
            )
        }
    ) { paddingValues ->

        Laporan4Screen(
            paddingValues = paddingValues,
            tahun = tahun,
            onChange = { tahun = it.toString() },
            dataLaporan4 = state.response,
            isLoading = state.isLoading
        )

    }
}

@Composable
fun Laporan4Screen(
    paddingValues: PaddingValues,
    tahun: String,
    onChange: (Int) -> Unit,
    dataLaporan4 : List<DataLaporan4>,
    isLoading: Boolean
) {
    val column1Weight = .1f
    val column2Weight = .2f
    val column3Weight = .2f
    val column4Weight = .4f
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        DropdownLaporan(state = tahun, onChange = onChange)
        if(isLoading) {
            SpinnerBlue()
        }else {


            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .clip(Shapes().extraSmall)
                            .background(successTable)
                    ) {
                        TableHeader(text = "No", weight = column1Weight)
                        TableHeader(text = "Nama", weight = column2Weight)
                        TableHeader(text = "Jumlah Reservasi", weight = column3Weight)
                        TableHeader(text = "Total Pembayaran", weight = column4Weight)
                    }
                    TableLaporan4(
                        column1Weight = column1Weight,
                        column2Weight = column2Weight,
                        column3Weight = column3Weight,
                        column4Weight = column4Weight,
                        dataLaporan4 = dataLaporan4,
                    )
                }

            }
        }
    }
}

@Composable
fun TableLaporan4(
    column1Weight : Float,
    column2Weight : Float,
    column3Weight : Float,
    column4Weight : Float,
    dataLaporan4 : List<DataLaporan4>
) {

    LazyColumn(
        Modifier
            .clip(Shapes().small)) {

        items(dataLaporan4) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.no.toString(), weight = column1Weight)
                TableCell(text = it.nama, weight = column2Weight)
                TableCell(text = it.jumlahReservasi.toString(), weight = column3Weight)
                TableCell(text = numberFormatRupiah(it.totalPembayaran.toInt()), weight = column4Weight)

            }
        }
    }
}