package com.p3l.gah_mobile.screen.main.reservasi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p3l.gah_mobile.data.model.reservasi.DataDetailReservasi
import com.p3l.gah_mobile.repository.DataRepository
import com.p3l.gah_mobile.repository.ReservasiRepository
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailReservasiViewModel @Inject constructor(
    private val reservasiRepository: ReservasiRepository,
    private val dataRepository: DataRepository,
    private val saveStateHandle: SavedStateHandle,
): ViewModel() {
    private val _detailReservasiState : MutableStateFlow<DetailReservasiState> = MutableStateFlow(
        DetailReservasiState()
    )
    val detailReservasiState : MutableStateFlow<DetailReservasiState>
        get() = _detailReservasiState

    init {
        viewModelScope.launch {
            dataRepository.getToken().collectLatest {
                getDetailReservasi(it, saveStateHandle.get<String>("idReservasi")!!.toInt())
            }
        }
    }

    private  fun getDetailReservasi(token: String, id: Int){
        reservasiRepository.getDetailReservasi(token, id)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _detailReservasiState.value = DetailReservasiState(response = data.data)
                        }
                    }
                    is Resource.Error -> {
                        _detailReservasiState.value = DetailReservasiState(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _detailReservasiState.value = DetailReservasiState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}

data class DetailReservasiState(
    val response: DataDetailReservasi = DataDetailReservasi(),
    val isLoading: Boolean = false,
    val error: String = ""
)