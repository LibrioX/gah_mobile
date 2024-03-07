package com.p3l.gah_mobile.screen.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p3l.gah_mobile.data.model.auth.DataLogin
import com.p3l.gah_mobile.data.model.auth.DataRegister
import com.p3l.gah_mobile.repository.AuthRepository
import com.p3l.gah_mobile.repository.DataRepository
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataRepository: DataRepository
) : ViewModel(){
    private val _loginState : MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState : MutableStateFlow<LoginState>
        get() = _loginState

    private val _registerState : MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val registerState : MutableStateFlow<RegisterState>
        get() = _registerState

    init {
        Log.d("AuthViewModel", "AuthViewModel created!")
    }

    fun loginCustomer(username : String, password : String){
        authRepository.loginCustomer(username, password)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            dataRepository.setToken("Bearer ${data.token}")
                            _loginState.value = LoginState(response = data.data)
                        }

                    }
                    is Resource.Error -> {
                        _loginState.value = LoginState(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _loginState.value = LoginState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun registerCustomer(
        nama: String,
        email: String,
        noIdentitas: String,
        noTelepon: String,
        alamat: String,
        username: String,
        password: String
    ){
        authRepository.registerCustomer(nama, email, noIdentitas, noTelepon, alamat, username, password)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _registerState.value = RegisterState(response = result.data?.data)
                    }
                    is Resource.Error -> {
                        _registerState.value = RegisterState(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _registerState.value = RegisterState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

}

data class LoginState(
    val response: DataLogin? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)

data class RegisterState(
    val response: DataRegister? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)