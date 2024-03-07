package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLoading(
    isLoading  : Boolean,
    onClick   : () -> Unit,
    text      : String,
    enabled  : Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            if (!isLoading)  onClick()
        },
        shape = Shapes().small,
        enabled = enabled
    ) {
        if (isLoading) {
            Spinner()
        } else {
            Text(text = text)
        }
    }
}