package com.example.littlelemon.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.domain.repository.UserRepository
import com.example.littlelemon.presentation.screens.Home
import com.example.littlelemon.presentation.screens.Onboarding
import com.example.littlelemon.presentation.screens.Profile

@Composable
fun Navigation(navController: NavHostController, userRepository: UserRepository, modifier: Modifier) {

    val startDestination = if (userRepository.isAuthenticated()) {
        Destinations.Home.route
    } else {
        Destinations.Onboarding.route
    }

    NavHost(navController = navController, startDestination = startDestination){
        composable(Destinations.Onboarding.route) {
            Onboarding(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Destinations.Home.route) {
            Home(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Destinations.Profile.route) {
            Profile(
                modifier = modifier,
                navController = navController
            )
        }
    }
}