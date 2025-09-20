package com.becomedev.unliminetpro.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.becomedev.unliminetpro.data.navigation.ROUTE_HOME
import com.becomedev.unliminetpro.data.navigation.ROUTE_SPLASH
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember { Animatable(0f) }

    // Animation
    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000
            )
        )
        delay(1000) // Stay for 1.5s
        navController.navigate(ROUTE_HOME) {
            popUpTo(ROUTE_SPLASH) { inclusive = true }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    SplashScreen(rememberNavController())
}