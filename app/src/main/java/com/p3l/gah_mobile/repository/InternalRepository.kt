package com.p3l.gah_mobile.repository

import com.p3l.gah_mobile.data.api.ApiService
import com.p3l.gah_mobile.data.model.ResponseBase
import com.p3l.gah_mobile.data.model.internal.ResponseLaporan1
import com.p3l.gah_mobile.data.model.internal.ResponseLaporan4
import com.p3l.gah_mobile.data.model.internal.ResponseLoginInternal
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class InternalRepository @Inject constructor(
    private val authApi: ApiService,
) {
    fun loginPegawai(username: String, password: String) : Flow<Resource<ResponseLoginInternal>>  = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.loginPegawai(username, password)
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

    fun detailPegawai(token: String) : Flow<Resource<ResponseLoginInternal>>  = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.detailPegawai(token)
            Resource.Success(response)
        } catch (e: Exception){
            Resource.Error(e.message ?: "Error")
        }
        emit(result)
    }

    fun changePasswordPegawai(token: String, password: String, newPassword:  String) : Flow<Resource<ResponseBase>>  = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.changePasswordPegawai(token, password, newPassword)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 401){
                Resource.Error("Token tidak valid")
            } else if (e.code() == 400 ){
                Resource.Error("Proses Validasi Gagal")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }

    fun logoutPegawai(token: String) : Flow<Resource<ResponseBase>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.logoutPegawai(token)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 401){
                Resource.Error("Token tidak valid")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }

    fun getLaporan1(token: String, tahun : Int) : Flow<Resource<ResponseLaporan1>> = flow {
        emit(Resource.Loading())
        val result = try{
            val response = authApi.getLaporan1(token, tahun)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 401){
                Resource.Error("Token tidak valid")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }

    fun getLaporan4(token: String, tahun : Int) : Flow<Resource<ResponseLaporan4>> = flow {
        emit(Resource.Loading())
        val result = try{
            val response = authApi.getLaporan4(token, tahun)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 401){
                Resource.Error("Token tidak valid")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }
}