package com.example.kmmnoteapp.android.note_detail

class NoteDetailState (
    val noteTitle: String = "",
    val isNoteTitleHintVisible: Boolean = false,
    val noteContent: String = "",
    val isNoteContentHintFocussed: Boolean = false,
    val noteColor: Long = 0xFFFFFF
)