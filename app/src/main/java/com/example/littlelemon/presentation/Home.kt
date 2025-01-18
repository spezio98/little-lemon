package com.example.littlelemon.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.littlelemon.navigation.Destinations
import com.example.littlelemon.presentation.commoncomponents.Logo

@Composable
fun Home(
    modifier: Modifier,
    navController: NavHostController
) {
    Column(
        //verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.weight(0.25f))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .weight(0.5f).height(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile image",
                alignment = Alignment.CenterEnd,
                modifier = Modifier
                    .weight(0.25f)
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable{
                        navController.navigate(Destinations.Profile.route)
                    }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home(
        navController = NavHostController(LocalContext.current),
        modifier = Modifier
    )
}