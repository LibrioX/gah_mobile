package com.p3l.gah_mobile.data.model.auth

import com.google.gson.annotations.SerializedName

data class ResponseRegisterCustomer(

	@field:SerializedName("data")
	val data: DataRegister,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataRegister(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("no_identitas")
	val noIdentitas: String,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("no_telepon")
	val noTelepon: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("nama_institusi")
	val namaInstitusi: Any
)
