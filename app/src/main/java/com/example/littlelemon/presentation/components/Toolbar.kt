package com.example.littlelemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.presentation.navigation.Destinations

@Composable
fun Toolbar(
    modifier: Modifier,
    navController: NavHostController,
    showProfileButton: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(0.25f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .weight(0.5f, true)
                .height(50.dp)
        )
        Box(
            modifier = Modifier
                .weight(0.25f, true),
            contentAlignment = Alignment.CenterEnd
        ) {
            if(showProfileButton)
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile image",
                    alignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate(Destinations.Profile.route) {
                                launchSingleTop = true
                            }
                        }
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewToolbar() {
    Toolbar(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current),
        showProfileButton = true
    )
}
