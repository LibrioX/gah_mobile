package com.p3l.gah_mobile.repository

import com.p3l.gah_mobile.data.api.ApiService
import com.p3l.gah_mobile.data.model.reservasi.ResponseDetailReservasi
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class ReservasiRepository @Inject constructor(
    private val  authApi : ApiService
) {
    fun getDetailReservasi(token: String, id: Int) : Flow<Resource<ResponseDetailReservasi>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.getReservasi(token, id)
            Resource.Success(response)
        }  catch (e: HttpException){
            if(e.code() == 401){
                Resource.Error("Token tidak valid")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }
}