package com.example.littlelemon.presentation.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.domain.model.User
import com.example.littlelemon.presentation.theme.Primary
import com.example.littlelemon.presentation.theme.Secondary

@Composable
fun UserForm(
    user: User? = null,
    isReadOnly: Boolean = false,
    buttonText: String,
    onButtonClick: (user: User?) -> Unit
) {
    var firstName by remember { mutableStateOf(user?.firstName ?: "") }
    var lastName by remember { mutableStateOf(user?.lastName ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 30.dp)
    ) {
        // Title
        Text(
            text = stringResource(R.string.onboarding_form_title),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        // First Name
        Text(
            text = stringResource(R.string.onboarding_first_name),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = firstName,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = { firstName = it },
            readOnly = isReadOnly,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Last Name
        Text(
            text = stringResource(R.string.onboarding_last_name),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = lastName,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = { lastName = it },
            readOnly = isReadOnly,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Email
        Text(
            text = stringResource(R.string.onboarding_email),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = { email = it },
            readOnly = isReadOnly,
            modifier = Modifier.fillMaxWidth()
        )

        // Button
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    if (!isReadOnly && (firstName.isBlank() || lastName.isBlank() || email.isBlank())) {
                        Toast.makeText(context,
                            context.getString(R.string.unsuccessful_registration_text), Toast.LENGTH_SHORT).show()
                    } else {
                        onButtonClick(
                            User(firstName, lastName, email)
                        )
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
                Text(text = buttonText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserFormPreview() {
    UserForm(
        user = User(
            firstName = "John",
            lastName = "Doe",
            email = "william.henry.harrison@example-pet-store.com"
        ),
        isReadOnly = false,
        buttonText = "Log out",
        onButtonClick = {}
    )
}
