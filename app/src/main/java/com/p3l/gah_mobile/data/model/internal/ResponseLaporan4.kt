package com.p3l.gah_mobile.data.model.internal

import com.google.gson.annotations.SerializedName

data class ResponseLaporan4(

	@field:SerializedName("data")
	val data: List<DataLaporan4>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataLaporan4(

	@field:SerializedName("jumlah_reservasi")
	val jumlahReservasi: Int = 0,

	@field:SerializedName("no")
	val no: Int = 0,

	@field:SerializedName("nama")
	val nama: String = "",

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: String = ""
)
