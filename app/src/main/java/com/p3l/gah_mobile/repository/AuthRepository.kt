package com.p3l.gah_mobile.repository

import com.p3l.gah_mobile.data.api.ApiService
import com.p3l.gah_mobile.data.model.auth.ResponseLoginCustomer
import com.p3l.gah_mobile.data.model.auth.ResponseRegisterCustomer
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class AuthRepository @Inject constructor(
    private val authApi: ApiService
) {
    fun loginCustomer (username: String, password: String) : Flow<Resource<ResponseLoginCustomer>> = flow {
        emit(Resource.Loading())
        val result =  try{
            val response = authApi.loginCustomer(username, password)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 401){
                Resource.Error("Username atau password salah")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }

    fun registerCustomer (
      nama: String,
      email: String,
      noIdentitas: String,
      noTelepon: String,
      alamat: String,
      username: String,
      password: String
    ) : Flow<Resource<ResponseRegisterCustomer>> = flow {
        emit(Resource.Loading())
        val result =  try{
            val response = authApi.registerCustomer(nama, email, noIdentitas, noTelepon, alamat, username, password)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 400){
                Resource.Error("Proses Validasi Gagal")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }
}