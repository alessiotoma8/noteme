package com.example.noteme.composables.textnote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noteme.composables.common.LillaCard

@Composable
fun NoteTitleView() {
    val localDensity = LocalDensity.current
    var titleCardSeize by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LillaCard(
            modifier = Modifier
                .weight(1f)
                .onGloballyPositioned { layoutCoordinates ->
                    val height = layoutCoordinates.size.height
                    if (height < 0) return@onGloballyPositioned
                    val viewHeightDp = with(localDensity) { height.toDp() }
                    titleCardSeize = viewHeightDp.value.toInt()
                },
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                text = "Tile note",
                color = MaterialTheme.colorScheme.primary
            )
        }
        LillaCard(modifier = Modifier.size(titleCardSeize.dp)) {
            Box(Modifier.fillMaxSize()) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}