package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.SharedPreferencesRepository
import com.example.littlelemon.navigation.Navigation
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedPreferencesRepository = SharedPreferencesRepository.getSharedPreferenceRepository(this.applicationContext)
        setContent {
            LittleLemonTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    val navController = rememberNavController()
                    Navigation(navController, sharedPreferencesRepository, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
