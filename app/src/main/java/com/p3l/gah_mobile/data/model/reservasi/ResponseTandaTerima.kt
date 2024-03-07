package com.p3l.gah_mobile.data.model.reservasi

import com.google.gson.annotations.SerializedName

data class ResponseTandaTerima(

	@field:SerializedName("data")
	val data: TandaTerima = TandaTerima(),

	@field:SerializedName("message")
	val message: String = "",

	@field:SerializedName("status")
	val status: String = ""
)

data class LayananItem(

	@field:SerializedName("id_layanan")
	val idLayanan: Int = 0,

	@field:SerializedName("jumlah")
	val jumlah: Int = 0,

	@field:SerializedName("sub_total")
	val subTotal: Int = 0,

	@field:SerializedName("f_k_transaksi_fasilitas_fasilitas")
	val fKTransaksiFasilitasFasilitas: FKTransaksiFasilitasFasilitas = FKTransaksiFasilitasFasilitas(),

	@field:SerializedName("id_transaksi")
	val idTransaksi: Int = 0,

	@field:SerializedName("tanggal_menerima")
	val tanggalMenerima: String  = "",

	@field:SerializedName("id_reservasi")
	val idReservasi: Int = 0,
)

data class TandaTerima(

	@field:SerializedName("layanan")
	val layanan: List<LayananItem> = listOf(),

	@field:SerializedName("uang_jaminan")
	val uangJaminan: Int  = 0,

	@field:SerializedName("permintaan_khusus")
	val permintaanKhusus: String = "",

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: Int = 0,

	@field:SerializedName("pic")
	val pic: Any = Any(),

	@field:SerializedName("kamars")
	val kamars: List<KamarsItem> = listOf(),

	@field:SerializedName("title")
	val title: String="",

	@field:SerializedName("body")
	val body: String="",

	@field:SerializedName("tanggal_pembayaran")
	val tanggalPembayaran: Any = Any(),

	@field:SerializedName("alamat")
	val alamat: String="",

	@field:SerializedName("id_booking")
	val idBooking: String="",

	@field:SerializedName("dewasa")
	val dewasa: Int=0,

	@field:SerializedName("checkin")
	val checkin: String="",

	@field:SerializedName("nama")
	val nama: String="",

	@field:SerializedName("anak")
	val anak: Int=0,

	@field:SerializedName("tanggal")
	val tanggal: String="",

	@field:SerializedName("checkout")
	val checkout: String="",

	@field:SerializedName("email")
	val email: String="",
)

data class KamarsItem(

	@field:SerializedName("tarif")
	val tarif: Int =0,

	@field:SerializedName("total")
	val total: Int = 0,

	@field:SerializedName("jumlah")
	val jumlah: Int = 0,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int =0,

	@field:SerializedName("jenis_kamar")
	val jenisKamar: String="",
)

