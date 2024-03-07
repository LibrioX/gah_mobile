package com.p3l.gah_mobile.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowDown
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.p3l.gah_mobile.ui.component.MinimalDialog
import com.p3l.gah_mobile.ui.theme.iconColor
import java.time.LocalDate


@Composable
fun HomeParent(
    viewModel: MainViewModel,
    navigateToAvailability: () -> Unit,
){
    val state = viewModel.customerInformationState.collectAsState().value
    val checkIn = viewModel.detailReservasi.collectAsState().value.tanggalCheckIn
    val checkOut = viewModel.detailReservasi.collectAsState().value.tanggalCheckOut
    val dewasa = viewModel.detailReservasi.collectAsState().value.dewasa
    val anak = viewModel.detailReservasi.collectAsState().value.anak
    var openAlertDialog by remember { mutableStateOf(false) }
    HomeScreen(
        name = state.response?.nama ?: "",
        checkIn = checkIn,
        checkOut = checkOut,
        onTanggalChange = { checkIn, checkOut ->
            viewModel.setTanggal(checkIn, checkOut)
        },
        openDialog = {
            openAlertDialog = true
        },
    )

    if(openAlertDialog){
        MinimalDialog(
            onDismissRequest = {
                openAlertDialog = false
            },
            goToAvailability = {
                navigateToAvailability()
                openAlertDialog = false
            },
            dewasa = dewasa.toString(),
            anak = anak.toString(),
            onChangeDewasa = {
                viewModel.setDewasa(it.toInt())
            },
            onChangeAnak = {
                viewModel.setAnak(it.toInt())
            },
        )
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    name: String,
    checkIn: String,
    checkOut: String,
    onTanggalChange: (String, String) -> Unit,
    openDialog: () -> Unit,
) {


    val dateDialog = rememberUseCaseState()

    CalendarDialog(
        state = dateDialog,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary =  LocalDate.now()..LocalDate.now().plusDays(365),
        ),
        selection = CalendarSelection.Period { startDate, endDate ->
           onTanggalChange(startDate.toString(), endDate.toString())
        },
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Box(modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceTint))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ){
                Text(
                    text = "Hello, $name",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = "Mau menginap kapan?",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier =  Modifier.padding(horizontal = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Row(
                            modifier = Modifier
                                .clip(Shapes().small)
                                .weight(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Outlined.KeyboardDoubleArrowUp,
                                contentDescription = null,
                                tint = iconColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Column {
                                Text(
                                    text = "Check In",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.outline,
                                )
                                Text(
                                    text = checkIn,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier
                                .clip(Shapes().small)
                                .weight(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Outlined.KeyboardDoubleArrowDown,
                                contentDescription = null,
                                tint = iconColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Column {
                                Text(
                                    text = "Check Out",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.outline,
                                )
                                Text(
                                    text = checkOut,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            dateDialog.show()
                        },
                        shape = Shapes().small,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = null,
                            tint = iconColor
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            openDialog()
                        },
                        shape = Shapes().small,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            tint = iconColor
                        )
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Layanan Kami",
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = {}) {
                    Text(text = "See All", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }

}