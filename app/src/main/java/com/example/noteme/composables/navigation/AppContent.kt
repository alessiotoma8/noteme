package com.example.noteme.composables.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.example.noteme.composables.home.CreateNoteBtn
import com.example.noteme.composables.home.NoteTypeSheet
import com.example.noteme.composables.navigation.navigator.navigateToTextNote
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navController = navController)
        },
        floatingActionButton = {
            //if (navController.currentDestination?.route == Screen.Home.route) {
            CreateNoteBtn(
                onClickAction = {
                    coroutineScope.launch {
                        sheetState.show()
                    }
                })
        },
    ) { innerPadding ->
        MainNavHost(innerPadding = innerPadding, navController = navController)
        NoteTypeSheet(
            sheetState = sheetState,
            onNavigateToSelectedNote = {selectedNoteType ->
                navController.navigateToTextNote()
            }
        )
    }
}