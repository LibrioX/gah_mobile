package com.p3l.gah_mobile.screen.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p3l.gah_mobile.data.model.internal.UserInternal
import com.p3l.gah_mobile.repository.DataRepository
import com.p3l.gah_mobile.repository.InternalRepository
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginInternalViewModel @Inject constructor(
    private val internalRepository: InternalRepository,
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _loginState : MutableStateFlow<LoginInternalState> = MutableStateFlow(LoginInternalState())
    val loginState : MutableStateFlow<LoginInternalState>
        get() = _loginState

    fun loginInternal(username : String, password : String){
        internalRepository.loginPegawai(username, password)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            dataRepository.setToken("Bearer ${data.token}")
                            _loginState.value = LoginInternalState(response = data.user)
                        }

                    }
                    is Resource.Error -> {
                        _loginState.value = LoginInternalState(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _loginState.value = LoginInternalState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}

data class LoginInternalState(
    val response : UserInternal? = null,
    val isLoading : Boolean = false,
    val error : String = ""
)