package com.example.kmmnoteapp.android.node_list

import com.example.kmmnoteapp.domain.note.Note


data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)