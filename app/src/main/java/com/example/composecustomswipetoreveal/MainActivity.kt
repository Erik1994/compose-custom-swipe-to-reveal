package com.example.composecustomswipetoreveal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.composecustomswipetoreveal.ui.theme.ComposeCustomSwipeToRevealTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCustomSwipeToRevealTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}