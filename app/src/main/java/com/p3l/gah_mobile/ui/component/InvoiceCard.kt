package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.ui.theme.slate500

@Composable
fun InvoiceCard(
    modifier : Modifier = Modifier.padding(16.dp),
    idInvoice : String,
    totalHarga : String,
    totalPajak : String,
    totalLayanan : String,
    totalSemua : String,
    tanggalPelunasan : String,
    uangJaminan : String,
    totalDeposit : String,
) {
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
                .background(color =  MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(
                    text = "Id Invoice",
                    style = MaterialTheme.typography.labelMedium,
                    color = slate500
                )
                Text(
                    text = idInvoice,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Column {
                Text(
                    text = "Tanggal Pelunasan",
                    style = MaterialTheme.typography.labelMedium,
                    color = slate500
                )
                Text(
                    text = tanggalPelunasan,
                    style = MaterialTheme.typography.titleSmall
                )
            }

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
                Text(
                    text = "Total harga kamar",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "+$totalHarga",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total harga fasilitas",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "+$totalLayanan",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total pajak",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "+$totalPajak",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Jaminan",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "-$uangJaminan",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Deposit",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "-$totalDeposit",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }

        Divider(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Pembayaran",
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = "Rp $totalSemua",
                style = MaterialTheme.typography.titleLarge
            )
        }


    }
}