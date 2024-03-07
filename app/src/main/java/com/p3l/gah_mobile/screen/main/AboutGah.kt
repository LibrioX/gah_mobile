package com.p3l.gah_mobile.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.LocalLaundryService
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.R
import com.p3l.gah_mobile.ui.theme.iconColor

@Composable
fun AboutGah(
    modifier: Modifier = Modifier,
    navigateToAuth : () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = "About GAH",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.surfaceTint,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes().small),
            painter = painterResource(id = R.drawable.cover_landing_page),
            contentDescription = "cover landing page"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Grand Atma Hotel Yogyakarta, sebuah destinasi istimewa di jantung Yogyakarta, di mana kenyamanan bertemu keanggunan modern.",
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes().small),
            painter = painterResource(id = R.drawable.deluxe),
            contentDescription = "cover landing page"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Selama bertahun-tahun, Grand Atma telah membangun fondasi yang kokoh sebagai destinasi penginapan pilihan di Yogyakarta. Dengan dedikasi untuk kualitas, keindahan, dan pelayanan yang tulus, hotel ini terus mengukir cerita suksesnya dalam industri perhotelan..",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Beberapa fasilitas unggulan kami",
            style = MaterialTheme.typography.titleLarge,
        )
        Column(
            Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){

                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.Wifi,
                    contentDescription = null,
                    tint = iconColor
                )
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.Pool,
                    contentDescription = null,
                    tint = iconColor
                )
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector =Icons.Filled.LocalParking,
                    contentDescription = null,
                    tint = iconColor
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = null,
                    tint = iconColor)
                Icon(modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.LocalBar,
                    contentDescription = null,
                    tint = iconColor)

                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.LocalLaundryService,
                    contentDescription = null,
                    tint = iconColor
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                onClick = { navigateToAuth()},
                shape = Shapes().small,
                modifier = Modifier.fillMaxWidth().height(50.dp),
            ) {
                Text(
                    "Register Sekarang"
                    , style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text  = "Grand Atma Hotel"
                , style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
            Text(
                "Jl. Babarsari No.43, Janti, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281"
                , style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}