package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.p3l.gah_mobile.screen.main.Room
import com.p3l.gah_mobile.ui.theme.Gah_mobileTheme
import com.p3l.gah_mobile.ui.theme.dangerText
import com.p3l.gah_mobile.ui.theme.slate400
import com.p3l.gah_mobile.util.numberFormatRupiah

@Composable
fun JenisKamarCard(
    id: Int,
    jenisKamar: String,
    tarif: Int,
    sisa: String,
    image: String,
    quantity: Int,
    onProductIncreased: (Int) -> Unit,
    onProductDecreased: (Int) -> Unit,
    onAddRoom : (Room) -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = ImageRequest.Builder(LocalContext.current).data(image).crossfade(true)
                    .build(),
                contentDescription = "Jenis Kamar",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = modifier
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = jenisKamar,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = numberFormatRupiah(tarif),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = "/ per malam",
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.End
                        )
                    }
                }



                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Bed,
                                contentDescription = "",
                                tint = slate400
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "2 Dewasa",
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SquareFoot,
                                contentDescription = "",
                                tint = slate400
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "2 Dewasa",
                                style = MaterialTheme.typography.labelSmall,

                                )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.People,
                                contentDescription = "",
                                tint = slate400
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "2 Dewasa",
                                style = MaterialTheme.typography.labelSmall,

                                )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "$sisa kamar tersisa",
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.End,
                            color = dangerText
                        )

                        if (quantity == 0) {
                            Button(
                                onClick = { onAddRoom(
                                    Room(
                                        id = id,
                                        jenisKamar = jenisKamar,
                                        price = tarif,
                                        quantity = 1,
                                        image = image,
                                        totalPrice = tarif
                                    )
                                ) },
                                shape = Shapes().small,
                                modifier = Modifier.height(40.dp)
                            ) {
                                Text(
                                    text = "Pesan",
                                )
                            }
                        } else {
                            Counter(
                                orderId = id,
                                orderCount = quantity,
                                onProductIncreased = onProductIncreased,
                                onProductDecreased = onProductDecreased,
                                canAdd = quantity < sisa.toInt(),
                            )
                        }


                    }


                }

            }
        }
    }

}

@Preview
@Composable
fun JenisKamarCardPreview() {
    Gah_mobileTheme {
//        JenisKamarCard()
    }
}