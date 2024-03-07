package com.p3l.gah_mobile.data.api

import com.p3l.gah_mobile.data.model.ResponseBase
import com.p3l.gah_mobile.data.model.auth.ResponseLoginCustomer
import com.p3l.gah_mobile.data.model.auth.ResponseRegisterCustomer
import com.p3l.gah_mobile.data.model.availability.ResponseAvailability
import com.p3l.gah_mobile.data.model.internal.ResponseLaporan1
import com.p3l.gah_mobile.data.model.internal.ResponseLaporan4
import com.p3l.gah_mobile.data.model.internal.ResponseLoginInternal
import com.p3l.gah_mobile.data.model.main.ResponseAkunCustomer
import com.p3l.gah_mobile.data.model.order.ResponseFasilitas
import com.p3l.gah_mobile.data.model.reservasi.ReservasiPayload
import com.p3l.gah_mobile.data.model.reservasi.ResponseDetailReservasi
import com.p3l.gah_mobile.data.model.reservasi.ResponseTandaTerima
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("loginCustomer")
    suspend fun loginCustomer(
        @Field("username")
        username: String,
        @Field("password")
        password: String
    ): ResponseLoginCustomer

    @FormUrlEncoded
    @POST("registerCustomer")
    suspend fun registerCustomer(
        @Field("nama")
        nama:String,
        @Field("email")
        email:String,
        @Field("no_identitas")
        noIdentitas:String,
        @Field("no_telepon")
        noTelepon:String,
        @Field("alamat")
        alamat:String,
        @Field("username")
        username:String,
        @Field("password")
        password:String
    ): ResponseRegisterCustomer


    @GET("akunCustomer")
    suspend fun akunCustomer(
        @Header("Authorization") token: String,
    )  : ResponseAkunCustomer

    @FormUrlEncoded
    @PUT("customer/{id}")
    suspend fun updateCustomer(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Field("nama")
        nama:String,
        @Field("email")
        email:String,
        @Field("no_identitas")
        noIdentitas:String,
        @Field("no_telepon")
        noTelepon:String,
        @Field("alamat")
        alamat:String,
    ) : ResponseAkunCustomer

    @GET("reservasi/{id}")
    suspend fun getReservasi(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ) : ResponseDetailReservasi

    @POST("logoutCustomer")
    suspend fun logoutCustomer(
        @Header("Authorization") token: String,
    ) : ResponseBase

    @FormUrlEncoded
    @PUT("changePassword")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Field("password")
        password: String,
        @Field("new_password")
        newPassword: String,
    ) : ResponseBase

    @FormUrlEncoded
    @POST("loginPegawai")
    suspend fun loginPegawai(
        @Field("username")
        username: String,
        @Field("password")
        password: String
    ): ResponseLoginInternal

    @GET("detailPegawai")
    suspend fun detailPegawai(
        @Header("Authorization") token: String,
    ) : ResponseLoginInternal

    @FormUrlEncoded
    @PUT("changePasswordPegawai")
    suspend fun changePasswordPegawai(
        @Header("Authorization") token: String,
        @Field("password")
        password: String,
        @Field("new_password")
        newPassword: String,
    ) : ResponseBase

    @POST("logoutPegawai")
    suspend fun logoutPegawai(
        @Header("Authorization") token: String,
    ) : ResponseBase

    @FormUrlEncoded
    @POST("reservasi")
    suspend fun checkAvailableRoom(
        @Header("Authorization") token: String,
        @Field("tanggal_checkin")
        checkIn: String,
        @Field("tanggal_checkout")
        checkOut: String,
    ) : ResponseAvailability

    @GET("fasilitas")
    suspend fun getFasilitas(
        @Header("Authorization") token: String,
    ) : ResponseFasilitas

    @POST("insert")
    suspend fun createReservasi(
        @Header("Authorization") token: String,
        @Body reservasiPayload: ReservasiPayload,
    ) :ResponseBase

    @GET("tandaterima/{id}")
    suspend fun getTandaTerima(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ) : ResponseTandaTerima

    @FormUrlEncoded
    @PUT("changestatus/{id}")
    suspend fun changeStatusReservasi(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Field("status")
        status: String,
    ) : ResponseBase

    @GET("customerbaru/{tahun}")
    suspend fun getLaporan1(
        @Header("Authorization") token: String,
        @Path("tahun") id: Int,
    ) : ResponseLaporan1

    @GET("customerterbanyak/{tahun}")
    suspend fun getLaporan4(
        @Header("Authorization") token: String,
        @Path("tahun") id: Int,
    ) : ResponseLaporan4

}