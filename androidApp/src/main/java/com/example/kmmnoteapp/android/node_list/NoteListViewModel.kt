package com.example.kmmnoteapp.android.node_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmnoteapp.domain.note.Note
import com.example.kmmnoteapp.domain.note.NoteDataSource
import com.example.kmmnoteapp.domain.note.SearchNotes
import com.example.kmmnoteapp.domain.time.DateTimeUtil
import com.example.kmmnoteapp.presentation.RedOrangeHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private  val noteDataSource: NoteDataSource,
    private  val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private  val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())


    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value) {
            savedStateHandle["isSearchActive"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNodeById(id)
            loadNotes()
        }
    }
}