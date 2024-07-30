package com.example.kmmnoteapp.data.note

import com.example.kmmnoteapp.domain.note.Note
import com.example.kmmnoteapp.domain.note.NoteDataSource
import com.example.kmmnoteapp.domain.time.DateTimeUtil
import com.example.kmmnoteapp.sqldelight.database.NoteDatabase

class SqlDelightNodeDataSource(db: NoteDatabase): NoteDataSource {

    private  val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            note.id,
            note.title,
            note.content,
            note.colorHex,
            DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return  queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNodeById(id: Long) {
        queries.deleteNoteById(id)
    }
}