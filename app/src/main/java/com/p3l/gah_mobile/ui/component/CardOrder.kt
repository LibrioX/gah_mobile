package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.p3l.gah_mobile.util.numberFormatRupiah

@Composable
fun CardOrder(
    image: String,
    title: String,
    price : Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            modifier = Modifier
                .width(100.dp)
                .height(80.dp),
            model = ImageRequest.Builder(LocalContext.current).data(image).crossfade(true)
                .build(),
            contentDescription = "Jenis Kamar",
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )

        Text(
            text = numberFormatRupiah(price),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(end = 8.dp)
        )
    }
}