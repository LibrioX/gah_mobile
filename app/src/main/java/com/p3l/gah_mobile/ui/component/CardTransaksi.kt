package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.ui.theme.danger
import com.p3l.gah_mobile.ui.theme.dangerText
import com.p3l.gah_mobile.ui.theme.iconColor
import com.p3l.gah_mobile.ui.theme.primary
import com.p3l.gah_mobile.ui.theme.primaryText
import com.p3l.gah_mobile.ui.theme.secondary
import com.p3l.gah_mobile.ui.theme.secondaryText
import com.p3l.gah_mobile.ui.theme.slate500
import com.p3l.gah_mobile.ui.theme.success
import com.p3l.gah_mobile.ui.theme.successText
import com.p3l.gah_mobile.ui.theme.warning
import com.p3l.gah_mobile.ui.theme.warningText

@Composable
fun CardTransaksi(
    modifier: Modifier = Modifier.padding(16.dp),
    id: Int,
    idBooking: String,
    tanggalCheckin: String,
    tanggalCheckout: String,
    jumlahDewasa: Int,
    jumlahAnak: Int,
    permintaanKhusus: String,
    totalPembayaran: Int,
    status: String,
    onClick: () -> Unit,
    navigateToPembayaran: (Int) -> Unit,
    onOpenModal : (Int) -> Unit
) {

    val statusColor = mapOf(
        "BELUM DIBAYAR" to warning,
        "SUDAH DIBAYAR" to success,
        "IN" to primary,
        "OUT" to secondary,
        "BATAL" to danger,
        "BATAL DENDA" to danger,
    )

    val statusText = mapOf(
        "BELUM DIBAYAR" to warningText,
        "SUDAH DIBAYAR" to successText,
        "IN" to primaryText,
        "OUT" to secondaryText,
        "BATAL" to dangerText,
        "BATAL DENDA" to danger,
    )
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = statusColor[status]!!)
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Id Booking",
                    style = MaterialTheme.typography.labelMedium,
                    color = slate500
                )
                Text(
                    text = idBooking,
                    style = MaterialTheme.typography.titleSmall
                )
            }


            Text(
                text = status,
                style = MaterialTheme.typography.titleSmall,
                color = statusText[status]!!
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
                .padding(horizontal = 16.dp)
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
                        text = tanggalCheckin,
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
                        text = tanggalCheckout,
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
                    text = "$jumlahDewasa Dewasa",
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
                    text = "$jumlahAnak Anak",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Permintaan Khusus",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = permintaanKhusus,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Total",
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "$totalPembayaran",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
        )

        if (status == "BELUM DIBAYAR") {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = {
                    navigateToPembayaran(id)
                }) {
                    Text(
                        text = "Pembayaran",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.surfaceTint
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Outlined.AttachMoney,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                if (status == "SUDAH DIBAYAR") {
                    Box(
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        TextButton(onClick = {
                            onOpenModal(id)
                        }) {
                            Text(
                                text = "Pembatalan",
                                style = MaterialTheme.typography.bodySmall,
                                color = dangerText
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = null,
                                tint = dangerText
                            )
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(onClick = onClick) {
                        Text(
                            text = "See Detail",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Outlined.RemoveRedEye,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
            }

        }


    }
}