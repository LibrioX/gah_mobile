package com.p3l.gah_mobile.data.model.internal

import com.google.gson.annotations.SerializedName

data class ResponseLoginInternal(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: UserInternal,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("token")
	val token: String
)

data class UserInternal(

	@field:SerializedName("id_akun_internal")
	val idAkunInternal: Int,

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("username")
	val username: String
)
