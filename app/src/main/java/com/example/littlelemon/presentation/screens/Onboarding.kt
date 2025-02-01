package com.example.littlelemon.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.presentation.theme.Primary
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.littlelemon.domain.model.User
import com.example.littlelemon.presentation.navigation.Destinations
import com.example.littlelemon.presentation.components.Logo
import com.example.littlelemon.presentation.viewmodel.SharedViewModel
import com.example.littlelemon.presentation.theme.Secondary

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
        Logo()
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

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(user) {
        if (user != null) {
            Toast.makeText(context,
                context.getString(R.string.successful_registration_text), Toast.LENGTH_SHORT).show()
            navController.navigate(Destinations.Home.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 30.dp)
    ) {
        //Title
        Text(
            text = stringResource(R.string.onboarding_form_title),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp))

        //first name
        Text(
            text = stringResource(R.string.onboarding_first_name),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = firstName,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = {
                firstName = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp))

        //last name
        Text(
            text = stringResource(R.string.onboarding_last_name),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = lastName,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = {
                lastName = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp))

        //email
        Text(
            text = stringResource(R.string.onboarding_email),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        //Button
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    if(firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
                        viewModel.saveUser(User(
                            firstName = firstName,
                            lastName = lastName,
                            email = email
                        ))
                    }
                    else{
                        Toast.makeText(context,
                            context.getString(R.string.unsuccessful_registration_text), Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary,
                    contentColor = Primary
                ),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(width = 1.dp, color = Primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.onboarding_register_button_text))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingFormPreview() {
    OnboardingForm(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}