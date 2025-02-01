package com.example.littlelemon.presentation.navigation

interface Destinations {
    val route: String

    object Onboarding: Destinations {
        override val route = "onboarding"
    }

    object Home: Destinations {
        override val route = "home"
    }

    object Profile: Destinations {
        override val route = "profile"
    }

}