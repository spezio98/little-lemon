package com.example.littlelemon.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.presentation.components.Toolbar
import com.example.littlelemon.presentation.components.UserForm
import com.example.littlelemon.presentation.navigation.Destinations
import com.example.littlelemon.presentation.viewmodel.SharedViewModel

@Composable
fun Profile(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        //verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Toolbar(
            modifier = modifier,
            navController = navController,
            showProfileButton = false
        )
        ProfileInfo(
            navController,
            viewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    Profile(
        navController = rememberNavController(),
        modifier = Modifier,
        viewModel = hiltViewModel()
    )
}

@Composable
fun ProfileInfo(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val user by viewModel.user.collectAsState()

    UserForm(
        user = user,
        isReadOnly = true,
        buttonText = stringResource(R.string.logout_button_text),
        onButtonClick = {
            viewModel.clearDataAndNavigate {
                navController.navigate(Destinations.Onboarding.route) {
                    popUpTo(Destinations.Home.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoPreview() {
    ProfileInfo(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}