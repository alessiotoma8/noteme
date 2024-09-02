package com.example.noteme.presentation

import androidx.compose.ui.graphics.vector.ImageVector

data class NoteTypeItem(
    val type: Type,
    val text: String,
    val icon: ImageVector,
) {
    enum class Type {
        TEXT, TODO, SCHEDULED, PAINTED
    }
}