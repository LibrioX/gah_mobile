package com.p3l.gah_mobile.screen.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.internal.DataLaporan1
import com.p3l.gah_mobile.ui.component.DropdownLaporan
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.component.SpinnerBlue
import com.p3l.gah_mobile.ui.component.TableCell
import com.p3l.gah_mobile.ui.component.TableHeader
import com.p3l.gah_mobile.ui.theme.secondaryText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Laporan1Parent(
    viewModel: HomeInternalViewModel,
    navigateBack: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.laporan1State.collectAsState().value
    var tahun by remember { mutableStateOf("2023") }

    LaunchedEffect(key1 = tahun, block = {
        if(tahun.isEmpty()){
            viewModel.getLaporan1()
        } else {
            viewModel.getLaporan1(tahun.toInt())
        }
    })


    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateBack,
                title = "Laporan 1"
            )
        }
    ) { paddingValues ->

        Laporan1Screen(
            paddingValues = paddingValues,
            tahun = tahun,
            onChange = { tahun = it.toString() },
            dataLaporan1 = state.response,
            isLoading = state.isLoading
        )
    }
}

@Composable
fun Laporan1Screen(
    paddingValues: PaddingValues,
    tahun: String ,
    onChange: (Int) -> Unit,
    dataLaporan1 : List<DataLaporan1>,
    isLoading: Boolean
) {
    val column1Weight = .3f // 30%
    val column2Weight = .4f // 70%
    val column3Weight = .3f
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                text = "Total User",
                                style = MaterialTheme.typography.labelMedium,
                            )
                            Text(
                                text = if (dataLaporan1.isEmpty()) "0" else dataLaporan1[0].totalUser.toString(),
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .clip(Shapes().extraSmall)
                            .background(secondaryText)
                    ) {
                        TableHeader(text = "No", weight = column1Weight)
                        TableHeader(text = "Bulan", weight = column2Weight)
                        TableHeader(text = "Jumlah", weight = column3Weight)
                    }
                    TableLaporan1(
                        column1Weight = column1Weight,
                        column2Weight = column2Weight,
                        column3Weight = column3Weight,
                        dataLaporan1 = dataLaporan1,
                    )
                }

            }
        }
    }
}

@Composable
fun TableLaporan1(
    column1Weight : Float,
    column2Weight : Float,
    column3Weight : Float,
    dataLaporan1 : List<DataLaporan1>
) {

    LazyColumn(
        Modifier
            .clip(Shapes().small)) {

        items(dataLaporan1) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.no.toString(), weight = column1Weight)
                TableCell(text = it.bulan, weight = column2Weight)
                TableCell(text = it.jumlah.toString(), weight = column3Weight)

            }
        }
    }
}