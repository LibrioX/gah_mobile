package com.p3l.gah_mobile.data.model.main

import com.google.gson.annotations.SerializedName

data class ResponseAkunCustomer(

	@field:SerializedName("data")
	val data: DataAkunCustomer = DataAkunCustomer(),

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class FkCustomerReservasiItem(

	@field:SerializedName("tanggal_reservasi")
	val tanggalReservasi: String,

	@field:SerializedName("tanggal_checkout")
	val tanggalCheckout: String,

	@field:SerializedName("jumlah_anak")
	val jumlahAnak: Int,

	@field:SerializedName("uang_jaminan")
	val uangJaminan: Int,

	@field:SerializedName("permintaan_khusus")
	val permintaanKhusus: String? = "-",

	@field:SerializedName("id_invoice")
	val idInvoice: String,

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: Int,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("tanggal_pembayaran")
	val tanggalPembayaran: String,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int,

	@field:SerializedName("id_booking")
	val idBooking: String,

	@field:SerializedName("total_deposit")
	val totalDeposit: Int,

	@field:SerializedName("jumlah_dewasa")
	val jumlahDewasa: Int,

	@field:SerializedName("tanggal_checkin")
	val tanggalCheckin: String,

	@field:SerializedName("id_fo")
	val idFo: Int,

	@field:SerializedName("id_pic")
	val idPic: Int,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: String,

	@field:SerializedName("status")
	val status: String
)

data class DataAkunCustomer(

	@field:SerializedName("fk_customer_reservasi")
	val fkCustomerReservasi: List<FkCustomerReservasiItem> = emptyList(),

	@field:SerializedName("nama")
	val nama: String = "",

	@field:SerializedName("updated_at")
	val updatedAt: String= "",

	@field:SerializedName("created_at")
	val createdAt: String = "",

	@field:SerializedName("no_identitas")
	val noIdentitas: String  = "",

	@field:SerializedName("id_user")
	val idUser: Int = 0,

	@field:SerializedName("email")
	val email: String = "",

	@field:SerializedName("no_telepon")
	val noTelepon: String= "",

	@field:SerializedName("alamat")
	val alamat: String  = "",
)
