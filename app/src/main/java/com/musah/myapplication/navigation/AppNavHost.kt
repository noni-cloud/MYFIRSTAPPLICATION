package com.musah.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.musah.myapplication.ui.theme.screens.homescreen.HomeScreen
import com.musah.myapplication.ui.theme.screens.Loginscreen.LogInscreen
import com.musah.myapplication.ui.theme.screens.registerscreen.RegisterScreen
import com.musah.myapplication.ui.theme.screens.dashboard.DashboardScreen
import com.musah.myapplication.ui.theme.screens.splash.SplashScreen
import com.musah.myapplication.ui.theme.screens.Intent.Intentscreen
import com.musah.myapplication.ui.theme.screens.Calculator.CalculatorScreen
import com.musah.myapplication.ui.theme.screens.Safaricom.SafaricomScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController = navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(ROUTE_LOGIN) {
            LogInscreen(navController = navController)
        }
        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController = navController)
        }
        composable(ROUTE_INTENT) {
            Intentscreen(navController = navController)
        }
        composable(ROUTE_CALCULATOR) {
            CalculatorScreen(navController = navController)
        }
        composable(ROUTE_SAFARICOM) {
            SafaricomScreen(navController = navController)
        }
    }
}
