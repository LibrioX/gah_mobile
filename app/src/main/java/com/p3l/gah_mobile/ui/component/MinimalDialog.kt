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
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.p3l.gah_mobile.ui.theme.iconColor

@Composable
fun MinimalDialog(
    onDismissRequest: () -> Unit,
    goToAvailability : () -> Unit,
    dewasa : String,
    anak : String,
    onChangeDewasa : (String) -> Unit,
    onChangeAnak : (String) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = Shapes().small,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Detail Tamu",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Demo_ExposedDropdownMenuBox(
                    state = dewasa,
                    onChange = onChangeDewasa
                )
                Spacer(modifier = Modifier.height(8.dp))
                Demo_ExposedDropdownMenuBox(
                    state = anak,
                    onChange = onChangeAnak
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = "Batal")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .height(44.dp),
                        onClick = {
                            goToAvailability()
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
        }
    }
}