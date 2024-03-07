package com.p3l.gah_mobile.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.p3l.gah_mobile.screen.auth.LoginParent
import com.p3l.gah_mobile.screen.auth.RegisterParent
import com.p3l.gah_mobile.screen.internal.ChangePasswordPegawaiParent
import com.p3l.gah_mobile.screen.internal.HomeInternalParent
import com.p3l.gah_mobile.screen.internal.HomeInternalViewModel
import com.p3l.gah_mobile.screen.internal.Laporan1Parent
import com.p3l.gah_mobile.screen.internal.Laporan4Parent
import com.p3l.gah_mobile.screen.internal.LoginInternalParent
import com.p3l.gah_mobile.screen.main.AboutGah
import com.p3l.gah_mobile.screen.main.ChangePasswordParent
import com.p3l.gah_mobile.screen.main.EditProfileParent
import com.p3l.gah_mobile.screen.main.HomeParent
import com.p3l.gah_mobile.screen.main.MainViewModel
import com.p3l.gah_mobile.screen.main.ProfileParent
import com.p3l.gah_mobile.screen.main.ReservasiParent
import com.p3l.gah_mobile.screen.main.order.AvailabilityParent
import com.p3l.gah_mobile.screen.main.order.ReservasiBookingParent
import com.p3l.gah_mobile.screen.main.order.ReservasiPembayaranParent
import com.p3l.gah_mobile.screen.main.order.ReservasiReviewParent
import com.p3l.gah_mobile.screen.main.reservasi.DetailReservasiParent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.parent?.route
    val currentRoute = navBackStackEntry?.destination?.route
    fun popBackStack() {
        navController.popBackStack()
    }
    fun navigateToLogin() {
        navController.popBackStack()
    }

    fun navigateToRegister() {
        navController.navigate(Screen.Register.route)
    }
    fun navigateToMain() {
        navController.navigate(Screen.Main.route){
            popUpTo(Screen.Auth.route){
                inclusive = true
            }
        }
    }
    fun navigateToAuth(){
        navController.navigate(Screen.Auth.route){
            popUpTo(Screen.Main.route){
                inclusive = true
            }
        }
    }
    fun navigateToEditProfile(){
        navController.navigate(Screen.EditProfile.route)
    }

    fun navigateToAvailability(){
        navController.navigate(Screen.Availability.route)
    }

    fun navigateToReservasi(){
        navController.navigate(Screen.HistoriReservasi.route)
    }

    fun navigateToDetailReservasi(idReservasi : Int){
        navController.navigate(Screen.DetailReservasi.createRoute(idReservasi))
    }

    fun navigateToChangePassword(){
        navController.navigate(Screen.ChangePassword.route)
    }

    fun navigateToHomeInternal(){
        navController.popBackStack()
        navController.navigate(Screen.Internal.route)
    }

    fun navigateToLoginInternal(){
        navController.navigate(Screen.AdminLogin.route)
    }

    fun navigateToChangePasswordPegawai(){
        navController.navigate(Screen.ChangePasswordPegawai.route)
    }

    fun navigateToAbout(){
        navController.navigate(Screen.About.route)
    }

    fun navigateToBooking(){
        navController.navigate(Screen.ReservasiBooking.route)
    }

    fun navigateToReview(){
        navController.navigate(Screen.ReservasiReview.route)
    }

    fun navigateToPembayaran(){
        navController.navigate(Screen.ReservasiPembayaran.route)
    }

    fun navigateToLaporan1(){
        navController.navigate(Screen.Laporan1.route)
    }

    fun navigateToLaporan4(){
        navController.navigate(Screen.Laporan4.route)
    }

    Scaffold(
        bottomBar = {
            if(currentRoute == Screen.Home.route || currentRoute == Screen.Profile.route) {
                BottomBar(navController)
            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {


            navigation(startDestination = Screen.Login.route, route = Screen.Auth.route) {
                composable(Screen.Login.route) {

                    LoginParent (
                        navigateToRegister = {
                            navigateToRegister()
                        },
                        navigateToMain = {
                            navigateToMain()
                        },
                        navigateToLoginInternal = {
                            navigateToLoginInternal()
                        }
                    )
                }
                composable(Screen.Register.route) {
                    RegisterParent (
                        navigateToLogin = {
                            navigateToLogin()
                        },
                    )
                }

            }

            navigation(startDestination = Screen.Home.route, route = Screen.Main.route) {

                composable(Screen.Home.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    HomeParent(
                        viewModel = mainViewModel,
                        navigateToAvailability = {
                            navigateToAvailability()
                        }
                    )
                }

                composable(Screen.Profile.route){ backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    ProfileParent(
                        navigateToAuth = {
                            navigateToAuth()
                        },
                        viewModel = mainViewModel,
                        navigateToEditProfile = {
                            navigateToEditProfile()
                        },
                        navigateToReservasi = {
                            navigateToReservasi()
                        },
                        navigateToChangePassword = {
                            navigateToChangePassword()
                        },
                        navigateToAbout ={
                            navigateToAbout()
                        }
                    )
                }

                composable(Screen.EditProfile.route){  backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    EditProfileParent(
                        viewModel = mainViewModel,
                        navigateToProfile = {
                            popBackStack()
                        }
                    )
                }

                composable(Screen.HistoriReservasi.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    ReservasiParent(
                        viewModel = mainViewModel,
                        navigateToProfile = {
                            popBackStack()
                        },
                        navigateToDetailReservasi = {
                            navigateToDetailReservasi(it)
                        },
                        navigateToPembayaran= {
                            navigateToPembayaran()
                        }
                    )
                }

                composable(Screen.DetailReservasi.route){
                    DetailReservasiParent(
                        navigateToProfile = {
                            popBackStack()
                        }
                    )
                }

                composable(Screen.ChangePassword.route){  backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    ChangePasswordParent(
                        viewModel = mainViewModel,
                        navigateToProfile = {
                            popBackStack()
                        }
                    )
                }

                composable(Screen.Availability.route){  backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    AvailabilityParent(
                        viewModel = mainViewModel,
                        navigateToHome = {
                            popBackStack()
                        },
                        navigateToBooking = {
                            navigateToBooking()
                        }
                    )
                }

                composable(Screen.ReservasiBooking.route){  backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    ReservasiBookingParent(
                        viewModel = mainViewModel,
                        onBack = {
                            popBackStack()
                        },
                        navigateToReview = {
                            navigateToReview()
                        }
                    )
                }

                composable(Screen.ReservasiReview.route){  backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    ReservasiReviewParent(
                        viewModel = mainViewModel,
                        onBack = {
                            popBackStack()
                        },
                        navigateToPembayaran = {
                            navigateToPembayaran()
                        }
                    )
                }

                composable(Screen.ReservasiPembayaran.route){  backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Main.route)
                    }
                    val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
                    ReservasiPembayaranParent(
                        viewModel = mainViewModel,
                        onBack = {
                            popBackStack()
                        },
                    )
                }
            }

            composable(Screen.AdminLogin.route){
                LoginInternalParent (

                    navigateToHome = {
                        navigateToHomeInternal()
                    },
                    navigateToLogin = {
                        navigateToLogin()
                    }
                )
            }

            composable(Screen.About.route){
                AboutGah(
                    navigateToAuth = {
                        navigateToAuth()
                    }
                )
            }
            navigation(startDestination = Screen.HomeInternal.route, route = Screen.Internal.route) {


                composable(Screen.HomeInternal.route){ backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Internal.route)
                    }
                    val viewModel = hiltViewModel<HomeInternalViewModel>(parentEntry)
                    HomeInternalParent(
                        viewModel = viewModel,
                        navigateToChangePasswordPegawai = {
                            navigateToChangePasswordPegawai()
                        },
                        logoutNavigate = {
                            popBackStack()
                        },
                        navigateToLaporan1 = {
                            navigateToLaporan1()
                        },
                        navigateToLaporan4 = {
                            navigateToLaporan4()
                        }
                    )
                }

                composable(Screen.ChangePasswordPegawai.route) {backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Internal.route)
                    }
                    val viewModel = hiltViewModel<HomeInternalViewModel>(parentEntry)
                    ChangePasswordPegawaiParent(
                        viewModel = viewModel,
                        navigateToProfile = {
                            popBackStack()
                        },
                    )
                }

                composable(Screen.Laporan1.route) {backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Internal.route)
                    }
                    val viewModel = hiltViewModel<HomeInternalViewModel>(parentEntry)
                    Laporan1Parent(
                        viewModel = viewModel) {
                        popBackStack()
                    }
                }

                composable(Screen.Laporan4.route) {backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.Internal.route)
                    }
                    val viewModel = hiltViewModel<HomeInternalViewModel>(parentEntry)
                    Laporan4Parent(viewModel = viewModel) {
                        popBackStack()
                    }
                }
            }


        }
    }

}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Outlined.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Outlined.AccountCircle,
            screen = Screen.Profile
        ),
    )

    NavigationBar {
        navigationItems.map {  item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(
                    modifier = Modifier.height(16.dp),
                    text = item.title,
                    style = MaterialTheme.typography.labelSmall
                ) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surfaceTint,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.surfaceTint,
                )
            )
        }
    }
}

//fun NavGraphBuilder.loginRoute(
//    navigateToRegister: () -> Unit,
//) {
//    composable(route = Screen.Login.route) {
//        LoginParent(
//            navigateToRegister = navigateToRegister,
//        )
//    }
//}
//
//fun NavGraphBuilder.registerRoute(
//    navigateToLogin: () -> Unit,
//) {
//    composable(route = Screen.Register.route) {
//        RegisterParent(
//            navigateToLogin = navigateToLogin,
//        )
//    }
//}
