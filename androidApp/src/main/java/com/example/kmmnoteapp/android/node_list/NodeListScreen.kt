package com.example.kmmnoteapp.android.node_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NodeListScreen(
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadNotes()
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add note", tint = Color.White)
        }
    }) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding),) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HideableTextField(
                    text = state.searchText,
                    isSearchActive = state.isSearchActive,
                    onTextChange = viewModel::onSearchTextChange,
                    onSearchChange = viewModel::onToggleSearch,
                    onCloseClick = viewModel::onToggleSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                this@Column.AnimatedVisibility(visible = !state.isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(text = "All notes", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = state.notes,
                    key = { it.id!! }
                ) { note ->
                    NoteItem(
                        note = note,
                        backgroundColor = Color(note.colorHex),
                        onNoteClick = { /*TODO*/ },
                        onDeleteClick = { viewModel.deleteNoteById(note.id!!) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement()
                    )
                }
            }
        }

    }
}