package com.p3l.gah_mobile.data.model.order

import com.google.gson.annotations.SerializedName

data class ResponseFasilitas(

	@field:SerializedName("data")
	val data: List<DataFasilitas>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataFasilitas(

	@field:SerializedName("id_layanan")
	val idLayanan: Int,

	@field:SerializedName("tarif")
	val tarif: Int,

	@field:SerializedName("satuan")
	val satuan: String,

	@field:SerializedName("nama_layanan")
	val namaLayanan: String
)
