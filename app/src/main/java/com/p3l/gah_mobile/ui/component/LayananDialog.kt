package com.p3l.gah_mobile.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.p3l.gah_mobile.data.model.order.DataFasilitas
import com.p3l.gah_mobile.screen.main.Layanan
import com.p3l.gah_mobile.ui.theme.primaryText

@Composable
fun LayananDialog(
    onDismissDialog : () -> Unit,
    listFasilitas : List<DataFasilitas>,
    quantityReturn : (Int) -> Int,
    onProductIncreased: (Int) -> Unit,
    onProductDecreased: (Int) -> Unit,
    onAddLayanan : (Layanan) -> Unit,
) {
    Log.d("LayananDialog", "LayananDialog: $listFasilitas")

        Dialog(onDismissRequest = onDismissDialog, properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )) {
            // Custom layout for the dialog
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(0.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(onClick = { onDismissDialog() }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "Close",
                                tint = primaryText
                            )
                        }
                        Text(
                            text = "Tambah Layanan",
                            style = MaterialTheme.typography.displayMedium,
                        )
                    }

                    LazyColumn(
                    ){
                        items(
                            listFasilitas,
                            key = { it.idLayanan}
                        ){ layanan ->
                            LayananCard(
                                id = layanan.idLayanan,
                                namaLayanan = layanan.namaLayanan,
                                tarif = layanan.tarif,
                                satuan = layanan.satuan,
                                quantity = quantityReturn(layanan.idLayanan),
                                onProductIncrease = onProductIncreased,
                                onProductDecrease = onProductDecreased,
                                onAddLayanan = onAddLayanan
                            )
                        }
                    }
                }
            }
        }
}