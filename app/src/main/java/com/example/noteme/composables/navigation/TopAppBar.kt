package com.example.noteme.composables.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.noteme.composables.navigation.navigator.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TopAppBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    CenterAlignedTopAppBar(
        title = {
            Column {
                Text(
                    text = "NoteMe!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Never forget yur thinkink",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        navigationIcon = {
            AnimatedContent(
                targetState = currentDestination,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "Btn Anim"
            ) { targetState ->
                if (targetState != Screen.Home.route) {
                    AppBarBtn(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                } else {
                    AppBarBtn(
                        imageVector = Icons.Default.Menu,
                        onClick = {
                            //navController.navigate(Screen.Settings.route)
                        }
                    )
                }
            }
        }
    )
}

@Composable
private fun AppBarBtn(imageVector: ImageVector, onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .clickable { onClick() }
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        imageVector = imageVector,
        contentDescription = "User icon",
        tint = MaterialTheme.colorScheme.onPrimary,
    )
}