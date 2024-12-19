package com.example.firebaseauthprojesi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firebaseauthprojesi.ui.theme.FirebaseAuthProjesiTheme
import com.example.firebaseauthprojesi.viewmodel.AuthViewModel // ViewModel'in import edildiğinden emin olun

class MainActivity : ComponentActivity() {
    // AuthViewModel'i property delegate olarak tanımla
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseAuthProjesiTheme {
                Scaffold { paddingValues ->
                    MyAppNavigation(Modifier.padding(paddingValues), authViewModel)
                }
            }
        }
    }
}
