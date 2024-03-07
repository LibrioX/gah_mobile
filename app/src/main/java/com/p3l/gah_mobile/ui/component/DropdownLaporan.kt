package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.p3l.gah_mobile.util.generateYears

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownLaporan(
    state : String,
    onChange : (Int) -> Unit
) {
    val context = LocalContext.current
    val years = generateYears()
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = state,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                label = {
                    Text(text = "Tahun", style = MaterialTheme.typography.bodySmall)
                },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                years.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(
                            text = item.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        ) },
                        onClick = {
                            onChange(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}