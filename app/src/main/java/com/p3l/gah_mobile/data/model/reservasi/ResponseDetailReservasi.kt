package com.p3l.gah_mobile.data.model.reservasi

import com.google.gson.annotations.SerializedName

data class ResponseDetailReservasi(

	@field:SerializedName("data")
	val data: DataDetailReservasi,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class FKReservasiSM(

	@field:SerializedName("id_akun_internal")
	val idAkunInternal: Int? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class FKReservasiFasilitasItem(

	@field:SerializedName("id_layanan")
	val idLayanan: Int? = null,

	@field:SerializedName("jumlah")
	val jumlah: Int? = null,

	@field:SerializedName("sub_total")
	val subTotal: Int? = null,

	@field:SerializedName("f_k_transaksi_fasilitas_fasilitas")
	val fKTransaksiFasilitasFasilitas: FKTransaksiFasilitasFasilitas? = null,

	@field:SerializedName("id_transaksi")
	val idTransaksi: Int? = null,

	@field:SerializedName("tanggal_menerima")
	val tanggalMenerima: String? = null,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int? = null
)

data class FKReservasiCustomer(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("no_identitas")
	val noIdentitas: String? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("nama_institusi")
	val namaInstitusi: Any? = null
)

data class FKReservasiFO(

	@field:SerializedName("id_akun_internal")
	val idAkunInternal: Int? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class FKTransaksiKamarJenisKamar(

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int? = null,

	@field:SerializedName("jenis_kamar")
	val jenisKamar: String? = null
)

data class FKReservasiTransaksiKamarItem(

	@field:SerializedName("f_k_transaksi_kamar_jenis_kamar")
	val fKTransaksiKamarJenisKamar: FKTransaksiKamarJenisKamar? = null,

	@field:SerializedName("id_transaksi_kamar")
	val idTransaksiKamar: Int? = null,

	@field:SerializedName("no_kamar")
	val noKamar: Int? = null,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int? = null,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int? = null,

	@field:SerializedName("tipe_bed")
	val tipeBed: String? = null
)

data class FKTransaksiFasilitasFasilitas(

	@field:SerializedName("id_layanan")
	val idLayanan: Int? = null,

	@field:SerializedName("tarif")
	val tarif: Int? = null,

	@field:SerializedName("satuan")
	val satuan: String? = null,

	@field:SerializedName("nama_layanan")
	val namaLayanan: String? = null
)

data class DataDetailReservasi(

	@field:SerializedName("tanggal_reservasi")
	val tanggalReservasi: String? = null,

	@field:SerializedName("tanggal_checkout")
	val tanggalCheckout: String? = null,

	@field:SerializedName("jumlah_anak")
	val jumlahAnak: Int? = null,

	@field:SerializedName("f_k_reservasi_customer")
	val fKReservasiCustomer: FKReservasiCustomer? = null,

	@field:SerializedName("uang_jaminan")
	val uangJaminan: Int? = null,

	@field:SerializedName("permintaan_khusus")
	val permintaanKhusus: Any? = null,

	@field:SerializedName("id_invoice")
	val idInvoice: Any? = null,

	@field:SerializedName("f_k_reservasi_s_m")
	val fKReservasiSM: FKReservasiSM? = null,

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("tanggal_pembayaran")
	val tanggalPembayaran: String? = null,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int? = null,

	@field:SerializedName("id_booking")
	val idBooking: String? = null,

	@field:SerializedName("f_k_reservasi_f_o")
	val fKReservasiFO: FKReservasiFO? = null,

	@field:SerializedName("total_deposit")
	val totalDeposit: Int? = null,

	@field:SerializedName("jumlah_dewasa")
	val jumlahDewasa: Int? = null,

	@field:SerializedName("f_k_reservasi_fasilitas")
	val fKReservasiFasilitas: List<FKReservasiFasilitasItem> = emptyList(),

	@field:SerializedName("tanggal_checkin")
	val tanggalCheckin: String? = null,

	@field:SerializedName("id_fo")
	val idFo: Int? = null,

	@field:SerializedName("f_k_reservasi_invoice")
	val fKReservasiInvoice: FKReservasiInvoice? = null,

	@field:SerializedName("id_pic")
	val idPic: Int? = null,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("f_k_reservasi_transaksi_kamar")
	val fKReservasiTransaksiKamar: List<FKReservasiTransaksiKamarItem> = emptyList()
)

data class FKReservasiInvoice(

	@field:SerializedName("total_semua")
	val totalSemua: Int? = null,

	@field:SerializedName("id_akun_internal")
	val idAkunInternal: Int? = null,

	@field:SerializedName("total_layanan")
	val totalLayanan: Int? = null,

	@field:SerializedName("id_invoice")
	val idInvoice: String? = null,

	@field:SerializedName("total_harga")
	val totalHarga: Int? = null,

	@field:SerializedName("total_pajak")
	val totalPajak: Int? = null,

	@field:SerializedName("tanggal_pelunasan")
	val tanggalPelunasan: String? = null
)
