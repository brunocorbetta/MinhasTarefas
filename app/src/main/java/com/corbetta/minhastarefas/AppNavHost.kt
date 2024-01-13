package com.corbetta.minhastarefas

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.corbetta.minhastarefas.ui.AddTask
import com.corbetta.minhastarefas.ui.Home

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            Home(
                navController = navController
            )
        }

        composable("AddTask") {
            AddTask()
        }
    }


}