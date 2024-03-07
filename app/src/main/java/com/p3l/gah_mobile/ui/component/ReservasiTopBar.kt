package com.p3l.gah_mobile.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiTopBar(
    title : String,
    active : Int,
    scrollBehavior: TopAppBarScrollBehavior,
    onMenuClicked: () -> Unit,
) {

    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors =  TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceTint,
        ),
        title = {
            Column{
                Text(
                    text = title,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Progress(active)
            }

        },
        navigationIcon = {
            IconButton(onClick = onMenuClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Icon", tint = MaterialTheme.colorScheme.onSecondary)
            }
        },
    )
}