package com.example.firebaseauthprojesi.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseauthprojesi.viewmodel.AuthState
import com.example.firebaseauthprojesi.viewmodel.AuthViewModel

@Composable
fun HomePage(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel){

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.UnAuthenticated -> {
                navController.navigate("login")
            }
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ana Sayfa", fontSize =32.sp)
        Button(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Çıkış Yap")
        }
    }

}