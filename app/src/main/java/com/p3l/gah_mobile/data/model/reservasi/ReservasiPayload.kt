package com.p3l.gah_mobile.data.model.reservasi

import com.p3l.gah_mobile.screen.main.Layanan

data class ReservasiPayload(
    val id_user: Int,
    val tanggal_checkin: String,
    val tanggal_checkout: String,
    val jumlah_dewasa: Int,
    val jumlah_anak: Int,
    val total_pembayaran: Int,
    val permintaan_khusus: String,
    val kamars: List<Int>,
    val layanan: List<Layanan>
)