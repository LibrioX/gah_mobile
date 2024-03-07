package com.p3l.gah_mobile.repository

import com.p3l.gah_mobile.data.api.ApiService
import com.p3l.gah_mobile.data.model.ResponseBase
import com.p3l.gah_mobile.data.model.availability.ResponseAvailability
import com.p3l.gah_mobile.data.model.main.ResponseAkunCustomer
import com.p3l.gah_mobile.data.model.order.ResponseFasilitas
import com.p3l.gah_mobile.data.model.reservasi.ReservasiPayload
import com.p3l.gah_mobile.data.model.reservasi.ResponseTandaTerima
import com.p3l.gah_mobile.screen.main.Layanan
import com.p3l.gah_mobile.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    private val authApi : ApiService
) {
    fun getAkunCustomer (token: String) : Flow<Resource<ResponseAkunCustomer>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.akunCustomer(token)
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

    fun updateCustomer(
        token: String,
        id: String,
        nama:String,
        email:String,
        noIdentitas:String,
        noTelepon:String,
        alamat:String,
    ) : Flow<Resource<ResponseAkunCustomer>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.updateCustomer(token, id, nama, email, noIdentitas, noTelepon, alamat)
            Resource.Success(response)
        } catch (e: HttpException){
            if(e.code() == 400){
                Resource.Error("Proses validasi gagal")
            } else {
                Resource.Error(e.message ?: "Error")
            }
        }
        emit(result)
    }

    fun logoutCustomer(token: String) : Flow<Resource<ResponseBase>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.logoutCustomer(token)
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

    fun changePassword(token:String, password: String, newPassword:String) : Flow<Resource<ResponseBase>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.changePassword(token, password, newPassword)
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

    fun checkAvailability(token: String, checkIn: String, checkOut: String) : Flow<Resource<ResponseAvailability>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.checkAvailableRoom(token, checkIn, checkOut)
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

    fun getFasilitas(token: String) :Flow<Resource<ResponseFasilitas>> = flow {
        emit(Resource.Loading())
        val result = try{
            val response = authApi.getFasilitas(token)
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

    fun createReservasi(
        token: String,
        idCustomer : Int,
        checkIn : String,
        checkOut : String,
        dewasa:Int,
        anak:Int,
        totalPembayaran:Int,
        permintaan:String,
        kamars : List<Int>,
        layanans : List<Layanan>,
    ) : Flow<Resource<ResponseBase>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.createReservasi(token, ReservasiPayload(idCustomer, checkIn, checkOut, dewasa, anak, totalPembayaran, permintaan, kamars, layanans))
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

    fun getTandaTerima(token: String, idReservasi: Int) : Flow<Resource<ResponseTandaTerima>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.getTandaTerima(token, idReservasi)
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

    fun changeStatusReservasi(token: String, idReservasi: Int, status: String) : Flow<Resource<ResponseBase>> = flow{
        emit(Resource.Loading())
        val result = try{
            val response = authApi.changeStatusReservasi(token, idReservasi, status)
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
}