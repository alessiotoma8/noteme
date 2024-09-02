package com.example.noteme.composables.navigation.navigator

sealed class Screen(val route: String) {
    data object Home : Screen("welcome")
    data object Settings : Screen("welcome")
    data object TextNote : Screen("text_note")
}