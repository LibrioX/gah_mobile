package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRightAlt
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.reservasi.KamarsItem
import com.p3l.gah_mobile.data.model.reservasi.LayananItem
import com.p3l.gah_mobile.ui.theme.iconColor
import com.p3l.gah_mobile.ui.theme.successText
import com.p3l.gah_mobile.util.numberFormatRupiah
import com.p3l.gah_mobile.util.totalNights

@Composable
fun RincianCard2(
    idBooking : String,
    checkin : String,
    checkout : String,
    dewasa : String,
    anak : String,
    rooms : List<KamarsItem>,
    layanan : List<LayananItem>,
    modifier : Modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceTint)
        .padding(16.dp),
) {
    val totalNights = totalNights(checkin, checkout)
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = "Rincian Pemesanan",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = idBooking,
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Divider(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Check In",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = checkin,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Outlined.ArrowRightAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
                Column {
                    Text(
                        text = "Check Out",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = checkout,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Outlined.People,
                    contentDescription = null,
                    tint = iconColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$dewasa Dewasa",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row {
                Icon(
                    imageVector = Icons.Outlined.ChildCare,
                    contentDescription = null,
                    tint = iconColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$anak Anak",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
            )
            Text(
                text = "Rincian Kamar",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                rooms.map {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "${it.jumlah} x Kamar ${it.jenisKamar}",
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Text(
                            text = numberFormatRupiah(it.total),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleMedium,
                )

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = numberFormatRupiah(rooms.sumOf { it.total }),
                        style = MaterialTheme.typography.bodyMedium,
                        color = successText
                    )

                    Text(
                        text = "/ malam",
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.End
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(
                    text = "Total $totalNights Malam",
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = "${numberFormatRupiah((totalNights * rooms.sumOf { it.total }).toInt())}",
                    style = MaterialTheme.typography.titleLarge,
                    color = successText
                )


            }

            if(layanan.isNotEmpty()){

                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                )

                Text(
                    text = "Rincian Fasilitas",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    layanan.map {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "${it.jumlah} x ${it.fKTransaksiFasilitasFasilitas.namaLayanan}",
                                style = MaterialTheme.typography.labelMedium,
                            )
                            Text(
                                text = numberFormatRupiah(it.subTotal),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = "${numberFormatRupiah(layanan.sumOf { it.subTotal })}",
                        style = MaterialTheme.typography.titleLarge,
                        color = successText
                    )

                }


            }

        }



    }
}