package com.example.noteme.composables.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable


@Composable
fun CreateNoteBtn(onClickAction: () -> Unit) {
    FloatingActionButton(onClick = {
        onClickAction()
    }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}