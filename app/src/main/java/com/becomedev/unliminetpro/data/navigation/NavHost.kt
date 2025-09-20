package com.becomedev.unliminetpro.data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.becomedev.unliminetpro.ui.home.HomeScreen
import com.becomedev.unliminetpro.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDesc : String = ROUTE_SPLASH,
){
    NavHost(navController = navController, startDestination = startDesc , modifier = modifier) {
        composable(ROUTE_SPLASH) { SplashScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController) }
    }
}


/*@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDesc: String = ROUTE_LOGIN
) {
    NavHost(navController = navController, startDestination = startDesc, modifier = modifier) {
        composable(ROUTE_LOGIN) {
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(viewModel = viewModel, navController = navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(viewModel,navController = navController)
        }
    }
}*/