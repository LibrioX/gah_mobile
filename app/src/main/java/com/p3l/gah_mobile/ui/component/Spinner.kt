package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spinner() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.onPrimary,
        strokeWidth = 2.dp,
        modifier = Modifier
            .size(24.dp)
    )
}

@Composable
fun SpinnerBlue() {
    Box(
        modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.surfaceTint,
            strokeWidth = 4.dp,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
    }

}

