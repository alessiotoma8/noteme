package com.example.noteme.composables.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.noteme.presentation.NoteTypeItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTypeSheet(
    sheetState: SheetState,
    onNavigateToSelectedNote: (NoteTypeItem.Type) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val noteTypes = remember {
        mutableStateListOf(
            NoteTypeItem(
                type = NoteTypeItem.Type.TEXT,
                text = "Text",
                icon = Icons.Default.Edit
            ),
            NoteTypeItem(
                type = NoteTypeItem.Type.TODO,
                text = "Todo",
                icon = Icons.Default.Check
            ),
            NoteTypeItem(
                type = NoteTypeItem.Type.SCHEDULED,
                text = "Schedule",
                icon = Icons.Default.DateRange
            ),
            NoteTypeItem(
                type = NoteTypeItem.Type.PAINTED,
                text = "Paint",
                icon = Icons.Default.Phone
            ),
        )
    }

    var selectedNoteType by remember { mutableStateOf<NoteTypeItem.Type?>(null) }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = if (selectedNoteType == null)
                        "Select one note type"
                    else "$selectedNoteType note",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                if (selectedNoteType == null) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(count = 2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(noteTypes) {
                            NoteTypeButton(
                                noteItem = it,
                                onClick = { selectedNoteType = it }
                            )
                        }
                    }
                } else {
                    EditabelNoteTitle(
                        onClick = {
                            selectedNoteType?.let { noteType ->
                                onNavigateToSelectedNote(noteType)
                                selectedNoteType = null
                                coroutineScope.launch {
                                    sheetState.hide()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EditabelNoteTitle(onClick: () -> Unit) {
    var textFiledValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val textFieldFocusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus()
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.focusRequester(textFieldFocusRequester),
            value = textFiledValue,
            onValueChange = { textFiledValue = it },
            label = {
                Text(text = "Title")
            }
        )
        FloatingActionButton(
            onClick = { onClick() }
        ) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Add")
        }
    }
}

@Composable
private fun NoteTypeButton(noteItem: NoteTypeItem, onClick: (NoteTypeItem.Type) -> Unit) {
    OutlinedButton(
        onClick = {
            onClick(noteItem.type)
        }) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = noteItem.icon, contentDescription = "")
            Text(text = noteItem.text)
        }
    }
}
