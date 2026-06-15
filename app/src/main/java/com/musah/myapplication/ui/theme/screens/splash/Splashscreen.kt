package com.musah.myapplication.ui.theme.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.musah.myapplication.R
import com.musah.myapplication.data.AuthViewModel
import com.musah.myapplication.navigation.ROUTE_DASHBOARD
import com.musah.myapplication.navigation.ROUTE_LOGIN
import com.musah.myapplication.navigation.ROUTE_SPLASH
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val authViewModel = AuthViewModel(navController, context)

    LaunchedEffect(key1 = true) {
        delay(2000)
        if (authViewModel.isLoggedIn()) {
            navController.navigate(ROUTE_DASHBOARD) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
            }
        } else {
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.arsenal),
            contentDescription = "App Logo",
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "MY APPLICATION",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
        Text(
            text = "Premium Quality Service",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
