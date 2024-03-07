package com.p3l.gah_mobile.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale

fun numberFormatRupiah(number: Int) : String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

    return formatter.format(number)
}

fun totalNights(checkin: String, checkout: String) : Long {
    val checkInDate = LocalDate.parse(checkin)

    val checkOutDate = LocalDate.parse(checkout)

    return ChronoUnit.DAYS.between(checkInDate, checkOutDate)
}

fun danaDikembalikan(tanggalCheckin: String): Boolean {
    val today = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val tanggalCekin = dateFormat.parse(tanggalCheckin)

    val tujuhHariSebelumCekin = Calendar.getInstance()
    tujuhHariSebelumCekin.time = tanggalCekin
    tujuhHariSebelumCekin.add(Calendar.DAY_OF_MONTH, -7)

    return today.before(tujuhHariSebelumCekin.time)
}

fun generateYears(): List<Int> {
    val currentYear = java.time.Year.now().value
    val minYear = currentYear - 9
    val years = mutableListOf<Int>()

    for (i in currentYear downTo minYear) {
        years.add(i)
    }

    return years
}