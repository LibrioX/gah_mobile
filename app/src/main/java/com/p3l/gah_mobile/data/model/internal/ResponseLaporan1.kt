package com.p3l.gah_mobile.data.model.internal

import com.google.gson.annotations.SerializedName

data class ResponseLaporan1(

	@field:SerializedName("data")
	val data: List<DataLaporan1>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataLaporan1(

	@field:SerializedName("total_User")
	val totalUser: Int = 0,

	@field:SerializedName("no")
	val no: Int = 0,

	@field:SerializedName("jumlah")
	val jumlah: Int = 0,

	@field:SerializedName("bulan")
	val bulan: String = ""
)
