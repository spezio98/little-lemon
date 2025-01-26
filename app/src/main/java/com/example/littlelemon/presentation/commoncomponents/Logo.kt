package com.example.littlelemon.presentation.commoncomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Little Lemon Logo",
        modifier = Modifier
            .padding(vertical = 20.dp)
            .size(width = 200.dp, height = 50.dp)

    )
}

@Preview(showBackground = true)
@Composable
fun LogoPreview() {
    Logo()
}