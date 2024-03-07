package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.p3l.gah_mobile.screen.main.Layanan
import com.p3l.gah_mobile.util.numberFormatRupiah

@Composable
fun LayananCard(
    id: Int,
    namaLayanan : String,
    tarif : Int,
    satuan : String,
    modifier : Modifier = Modifier.padding(16.dp),
    quantity : Int,
    onProductIncrease : (Int) -> Unit,
    onProductDecrease : (Int) -> Unit,
    onAddLayanan : (Layanan) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = ImageRequest.Builder(LocalContext.current).data("https://www.lalamove.com/hubfs/Memulai%20Bisnis%20Laundry%20Koin_%20Peluang%20Usaha%20yang%20Menguntungkan.jpg").crossfade(true)
                    .build(),
                contentDescription = "Jenis Kamar",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Row {
                    Text(
                        text = namaLayanan,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = numberFormatRupiah(tarif),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                        Text(
                            text = "/ $satuan",
                            style = MaterialTheme.typography.labelSmall,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Belum termasuk pajak",
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ){
                    if(quantity == 0){
                        OutlinedButton(
                            onClick = {
                                onAddLayanan(
                                    Layanan(
                                        id = id,
                                        nama_layanan = namaLayanan,
                                        price = tarif,
                                        quantity = 1,
                                        totalPrice = tarif
                                    )
                                )
                            },
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = "Add",
                            )
                        }
                    }else{
                        Counter(
                            orderId = id,
                            orderCount = quantity,
                            onProductIncreased = onProductIncrease,
                            onProductDecreased = onProductDecrease,
                            canAdd = true,
                        )
                    }

                }

            }
        }

    }
}