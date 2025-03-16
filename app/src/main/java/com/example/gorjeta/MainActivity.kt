package com.example.gorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gorjeta.ui.theme.GorjetaTheme
import androidx.compose.ui.Modifier
import com.example.gorjeta.screen.GorjetaScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GorjetaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GorjetaScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GorjetaPreview() {
    GorjetaTheme {
        GorjetaScreen()
    }
}