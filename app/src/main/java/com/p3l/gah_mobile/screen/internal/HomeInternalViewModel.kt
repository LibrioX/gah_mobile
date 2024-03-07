package com.p3l.gah_mobile.screen.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p3l.gah_mobile.data.model.internal.DataLaporan1
import com.p3l.gah_mobile.data.model.internal.DataLaporan4
import com.p3l.gah_mobile.data.model.internal.UserInternal
import com.p3l.gah_mobile.repository.DataRepository
import com.p3l.gah_mobile.repository.InternalRepository
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeInternalViewModel @Inject constructor(
    private val internalRepository: InternalRepository,
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _homeState : MutableStateFlow<HomeInternalState> = MutableStateFlow(HomeInternalState())
    val homeState : MutableStateFlow<HomeInternalState>
        get() = _homeState

    private val _token : MutableStateFlow<String> = MutableStateFlow("")
    private val getToken : MutableStateFlow<String>
        get() = _token

    private val _changePasswordState : MutableStateFlow<ChangePasswordPegawaiState> = MutableStateFlow(
        ChangePasswordPegawaiState()
    )
    val changePasswordState : MutableStateFlow<ChangePasswordPegawaiState>
        get() = _changePasswordState

    private val _logoutState : MutableStateFlow<LogoutPegawaiState> = MutableStateFlow(LogoutPegawaiState())
    val logoutState : MutableStateFlow<LogoutPegawaiState>
        get() = _logoutState

    private val _laporan1State : MutableStateFlow<Laporan1State> = MutableStateFlow(Laporan1State())
    val laporan1State : StateFlow<Laporan1State>
        get() = _laporan1State

    private val _laporan4State : MutableStateFlow<Laporan4State> = MutableStateFlow(Laporan4State())
    val laporan4State : StateFlow<Laporan4State>
        get() = _laporan4State

    init {
        viewModelScope.launch {
            dataRepository.getToken().collectLatest {
                _token.value = it
                detailPegawai(it)
            }
        }
    }

    fun detailPegawai(
        token:String
    ){
        internalRepository.detailPegawai(token)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _homeState.value = HomeInternalState(response = data.user)
                        }

                    }
                    is Resource.Error -> {
                        _homeState.value = HomeInternalState(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _homeState.value = HomeInternalState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun changePasswordPegawai(
        password:String,
        newPassword:String
    ){
        viewModelScope.launch {
            internalRepository.changePasswordPegawai(getToken.value, password, newPassword)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _changePasswordState.value = ChangePasswordPegawaiState(response = data.message, status = true)
                            }

                        }
                        is Resource.Error -> {
                            _changePasswordState.value = ChangePasswordPegawaiState(response = result.message!!, status = false)
                        }
                        is Resource.Loading -> {
                            _changePasswordState.value = ChangePasswordPegawaiState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun logoutPegawai(){
        viewModelScope.launch {
            internalRepository.logoutPegawai(getToken.value)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _logoutState.value = LogoutPegawaiState(response = data.message, status = data.status)
                            }
                        }
                        is Resource.Error -> {
                            _logoutState.value = LogoutPegawaiState(response = result.message!!, status = false)
                        }
                        is Resource.Loading -> {
                            _logoutState.value = LogoutPegawaiState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun getLaporan1(tahun : Int = 2023){
        viewModelScope.launch {
            internalRepository.getLaporan1(getToken.value, tahun)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _laporan1State.value = Laporan1State(response = data.data, status = false, isLoading = false)
                            }
                        }
                        is Resource.Error -> {
                            _laporan1State.value = Laporan1State(status = true)
                        }
                        is Resource.Loading -> {
                            _laporan1State.value = Laporan1State(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun getLaporan4(tahun : Int = 2023){
        viewModelScope.launch {
            internalRepository.getLaporan4(getToken.value, tahun)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _laporan4State.value = Laporan4State(response = data.data, status = false, isLoading = false)
                            }
                        }
                        is Resource.Error -> {
                            _laporan4State.value = Laporan4State(status = true)
                        }
                        is Resource.Loading -> {
                            _laporan4State.value = Laporan4State(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}

data class HomeInternalState(
    val response : UserInternal? = null,
    val isLoading : Boolean = false,
    val error : String = ""
)

data class ChangePasswordPegawaiState(
    val response: String = "",
    val isLoading: Boolean = false,
    val status: Boolean = false,
)

data class LogoutPegawaiState(
    val response: String = "",
    val isLoading: Boolean = false,
    val status: Boolean = false,
)

data class Laporan1State(
    val response: List<DataLaporan1> = listOf(),
    val isLoading: Boolean = false,
    val status: Boolean = false,
)

data class Laporan4State(
    val response: List<DataLaporan4> = listOf(),
    val isLoading: Boolean = false,
    val status: Boolean = false,
)