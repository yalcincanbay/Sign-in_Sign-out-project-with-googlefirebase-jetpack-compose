package com.example.firebaseauthprojesi.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.firebaseauthprojesi.viewmodel.AuthViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.firebaseauthprojesi.viewmodel.AuthState


@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    // State'ler:  e-posta ve şifre

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("home")
            }
            is AuthState.Error -> {
                Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {
                Unit
            }
        }
    }



    // Sayfa Tasarımı
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)), // Açık gri arka plan
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Başlık
            Text(
                text = "Kayıt Ol",
                fontSize = 28.sp,
                color = Color(0xFF6200EE),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // E-posta TextField
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-posta") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Şifre TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Şifre") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Kayıt Ol Butonu
            Button(
                onClick = {
                    authViewModel.signup(email, password)
                },
                enabled = authState.value != AuthState.Loading, // enabled parametresini doğru şekilde ekledik
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Kayıt Ol", color = Color.White)
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Giriş Yap Butonu
            TextButton(
                onClick = {
                    navController.navigate("login")
                }
            ) {
                Text("Zaten hesabınız var mı? Giriş yapın", color = Color.Gray)
            }
        }
    }
}

@Preview
@Composable
fun SignupPagePreview() {
    SignupPage(navController = NavController(LocalContext.current), authViewModel = AuthViewModel())
}