package com.p3l.gah_mobile.data.model.availability

import com.google.gson.annotations.SerializedName

data class ResponseAvailability(

	@field:SerializedName("data")
	val data: List<AvailabilityKamar>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class AvailabilityKamar(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("rincian_kamar")
	val rincianKamar: RincianKamar,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int,

	@field:SerializedName("jenis_kamar")
	val jenisKamar: String
)

data class RincianKamar(

	@field:SerializedName("tarif_dasar")
	val tarifDasar: Int,

	@field:SerializedName("tarif_season")
	val tarifSeason: Int,

	@field:SerializedName("available_rooms")
	val availableRooms: Int
)
