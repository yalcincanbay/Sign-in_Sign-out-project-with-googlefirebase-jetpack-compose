package com.example.firebaseauthprojesi

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthprojesi.pages.LoginPage
import androidx.compose.ui.Modifier
import com.example.firebaseauthprojesi.pages.HomePage
import com.example.firebaseauthprojesi.pages.SignupPage
import com.example.firebaseauthprojesi.viewmodel.AuthViewModel


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier ,authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login",builder = {
        composable("login"){
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup"){
            SignupPage(modifier,navController,authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
    })

}

