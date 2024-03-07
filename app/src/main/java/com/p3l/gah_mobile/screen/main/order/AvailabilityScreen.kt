package com.p3l.gah_mobile.screen.main.order

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.p3l.gah_mobile.data.model.availability.AvailabilityKamar
import com.p3l.gah_mobile.screen.main.MainViewModel
import com.p3l.gah_mobile.screen.main.Room
import com.p3l.gah_mobile.ui.component.HomeTopBar
import com.p3l.gah_mobile.ui.component.JenisKamarCard
import com.p3l.gah_mobile.ui.theme.primary
import com.p3l.gah_mobile.ui.theme.primaryText
import com.p3l.gah_mobile.util.numberFormatRupiah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailabilityParent(
    viewModel: MainViewModel,
    navigateToHome: () -> Unit,
    navigateToBooking: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.availabilityState.collectAsState().value
    val rooms = viewModel.roomState.collectAsState().value

    LaunchedEffect(block = {
        viewModel.checkAvailability()
    }, key1 = Unit)

    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehaviour,
                onMenuClicked = navigateToHome,
                title = "Kamar Tersedia"
            )
        }
    ) {
        AvailabilityScreen(
            listKamar = state.data,
            rooms = rooms,
            paddingValues = it,
            quantityReturn = { id ->
                viewModel.getCurrentQuantityById(id)
            },
            onProductIncreased = { id ->
                viewModel.increaseRoomQuantity(id)
            },
            onProductDecreased = { id ->
                viewModel.decreaseRoomQuantity(id)
            },
            onAddRoom = { room ->
                viewModel.addRoom(room)
            },
            navigateToBooking = navigateToBooking
        )
    }
}
@Composable
fun AvailabilityScreen(
    listKamar : List<AvailabilityKamar>,
    rooms : List<Room> = emptyList(),
    paddingValues: PaddingValues,
    quantityReturn : (Int) -> Int,
    onProductIncreased: (Int) -> Unit,
    onProductDecreased: (Int) -> Unit,
    onAddRoom : (Room) -> Unit,
    navigateToBooking: () -> Unit
) {
    var componentHeight by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    Box (
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
    ){
        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = componentHeight + paddingValues.calculateBottomPadding(),
            )
        ){
            items(
                listKamar,
                key = { it.idJenisKamar}
            ){ kamar ->
                JenisKamarCard(
                    id = kamar.idJenisKamar,
                    jenisKamar = kamar.jenisKamar,
                    tarif = kamar.rincianKamar.tarifDasar,
                    sisa = kamar.rincianKamar.availableRooms.toString(),
                    image = kamar.image,
                    quantity= quantityReturn(kamar.idJenisKamar),
                    onProductIncreased = onProductIncreased,
                    onProductDecreased = onProductDecreased,
                    onAddRoom = onAddRoom
                )
            }
        }


            AnimatedVisibility(
                visible = rooms.isNotEmpty(),
                enter = fadeIn() + slideInVertically(initialOffsetY = { 120 }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { 120 }),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier  = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(Shapes().small)
                        .clickable {
                            navigateToBooking()
                        }
                        .background(MaterialTheme.colorScheme.surfaceTint)
                        .onGloballyPositioned {
                            componentHeight = with(localDensity) { it.size.height.toDp() }
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    Alignment.CenterVertically
                ){
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Column(
                        ){
                            Text(
                                text ="Total Jenis Kamar",
                                style = MaterialTheme.typography.labelMedium,
                                color = primary
                            )

                            Text(
                                text = rooms.size.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Total Harga",
                                style = MaterialTheme.typography.labelMedium,
                                color = primary
                            )

                            Text(
                                text = numberFormatRupiah(rooms.sumOf { it.totalPrice }),
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = null,
                        tint = primaryText,
                        modifier = Modifier
                            .background(Color.White)
                            .padding(18.dp)
                    )

                }
            }

        if(rooms.isEmpty()){
            componentHeight = 0.dp
        }

    }



    Log.d("ROOMS", rooms.toString())
}