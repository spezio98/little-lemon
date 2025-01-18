package com.example.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.data.SharedPreferencesRepository
import com.example.littlelemon.presentation.Home
import com.example.littlelemon.presentation.Onboarding
import com.example.littlelemon.presentation.Profile

@Composable
fun Navigation(navController: NavHostController, sharedPreferencesRepository: SharedPreferencesRepository, modifier: Modifier) {

    val startDestination = if (sharedPreferencesRepository.isUserAuthenticated()) {
        Destinations.Home.route
    } else {
        Destinations.Onboarding.route
    }

    NavHost(navController = navController, startDestination = startDestination){
        composable(Destinations.Onboarding.route) {
            Onboarding(
                modifier = modifier,
                navController = navController,
                sharedPreferencesRepository = sharedPreferencesRepository
            )
        }

        composable(Destinations.Home.route) {
            Home()
        }

        composable(Destinations.Profile.route) {
            Profile(modifier)
        }
    }
}