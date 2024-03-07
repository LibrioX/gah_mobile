package com.p3l.gah_mobile.data.model

import com.google.gson.annotations.SerializedName

data class ResponseBase(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("data")
	val data: IdReservasi
)

data class IdReservasi(
	@field:SerializedName("id_reservasi")
	val idReservasi: Int = 0
)

