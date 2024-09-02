package com.example.noteme.composables.navigation.navigator

import androidx.navigation.NavHostController

fun NavHostController.navigateToTextNote() {
    navigate(Screen.TextNote.route)
}