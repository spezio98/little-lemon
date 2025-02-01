package com.example.littlelemon.presentation.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.presentation.components.Logo
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.presentation.navigation.Destinations
import com.example.littlelemon.presentation.viewmodel.SharedViewModel
import com.example.littlelemon.presentation.theme.Primary
import com.example.littlelemon.presentation.theme.Secondary

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
        Logo()
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
            value = user?.firstName ?: "",
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = {},
            readOnly = true,
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
            value = user?.lastName ?: "",
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = {},
            readOnly = true,
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
            value = user?.email ?: "",
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        //Button
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    viewModel.clearUser()
                    navController.navigate(Destinations.Onboarding.route)
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
                Text(text = stringResource(R.string.logout_button_text))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoPreview() {
    ProfileInfo(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}