package com.example.noteme.composables.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteme.composables.home.HomeScreen
import com.example.noteme.composables.navigation.navigator.Screen
import com.example.noteme.composables.textnote.TextNoteScreen

@Composable
fun MainNavHost(innerPadding:PaddingValues, navController: NavHostController) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.TextNote.route) {
            TextNoteScreen()
        }
    }

}