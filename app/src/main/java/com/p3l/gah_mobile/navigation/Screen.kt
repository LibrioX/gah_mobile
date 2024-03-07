package com.p3l.gah_mobile.navigation

sealed class Screen(val route: String) {
    object Auth  : Screen("auth")
    object Login : Screen("login")
    object Register : Screen("register")

    object Main : Screen("main")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object HistoriReservasi : Screen("histori_reservasi")
    object DetailReservasi : Screen("detail_reservasi?idReservasi={idReservasi}"){
        fun createRoute(idReservasi: Int) = "detail_reservasi?idReservasi=$idReservasi"
    }

    object ChangePassword : Screen("change_password")
    object Availability : Screen("availability")
    object ReservasiBooking : Screen("reservasi_booking")
    object ReservasiReview : Screen("reservasi_review")
    object ReservasiPembayaran : Screen("reservasi_pembayaran")

    object Internal : Screen("internal")
    object AdminLogin : Screen("admin_login")
    object HomeInternal : Screen("home_internal")
    object ChangePasswordPegawai : Screen("change_password_pegawai")

    object Laporan1 : Screen("laporan1")
    object Laporan4 : Screen("laporan4")

    object About : Screen("about")
}