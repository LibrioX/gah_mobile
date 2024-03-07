package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.ui.theme.primaryText

@Composable
fun Progress(
    active : Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(active == 1){
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "1",
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryText
                )
            }
        }else{
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(1.dp, Color.White, CircleShape)

            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "1",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }


        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Pesan",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .height(0.8.dp)
                .width(24.dp)
                .background(Color.White),
        )

        Spacer(modifier = Modifier.width(8.dp))

        if(active == 2){
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "2",
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryText
                )
            }
        }else{
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(1.dp, Color.White, CircleShape)

            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "2",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Review",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .height(0.8.dp)
                .width(24.dp)
                .background(Color.White),
        )
        Spacer(modifier = Modifier.width(8.dp))

        if(active == 3){
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "3",
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryText
                )
            }
        }else{
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(1.dp, Color.White, CircleShape)
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "3",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Bayar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}