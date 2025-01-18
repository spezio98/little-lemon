package com.example.littlelemon.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.presentation.commoncomponents.Logo
import androidx.compose.ui.Modifier

@Composable
fun Profile(modifier: Modifier) {
    Column(
        //verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Logo()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    Profile(modifier = Modifier)
}