package com.example.noteme.composables.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteme.composables.common.InfoCard
import com.example.noteme.composables.navigation.BaseScreen


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    BaseScreen(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp * 3)
        ) {
            InfoCard(
                title = "Welcome back!",
                text = "mario.rossi"
            )
            NoNotesBanner()
            Spacer(Modifier)
        }
    }
}