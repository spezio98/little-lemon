package com.example.littlelemon.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.presentation.theme.Primary
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.littlelemon.presentation.navigation.Destinations
import com.example.littlelemon.presentation.components.Toolbar
import com.example.littlelemon.presentation.components.UserForm
import com.example.littlelemon.presentation.viewmodel.SharedViewModel

@Composable
fun Onboarding(
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
        OnboardingTitle()
        OnboardingForm(
            navController,
            viewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(
        modifier = Modifier,
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}

@Composable
fun OnboardingTitle() {
    Text(
        text = stringResource(R.string.onboarding_title),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Primary)
            .padding(vertical = 30.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingTitlePreview() {
    OnboardingTitle()
}

@Composable
fun OnboardingForm(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(user) {
        if (user != null) {
            Toast.makeText(context,
                context.getString(R.string.successful_registration_text), Toast.LENGTH_SHORT).show()
            navController.navigate(Destinations.Home.route) {
                popUpTo(Destinations.Onboarding.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    UserForm(
        buttonText = stringResource(R.string.onboarding_register_button_text),
        onButtonClick = { user ->
            user?.let { viewModel.saveUser(it) }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingFormPreview() {
    OnboardingForm(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}