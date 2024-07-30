package com.example.kmmnoteapp.data.note

import com.example.kmmnoteapp.domain.note.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note {
    return Note(id,
    title,
    content,
    colorHex,
    Instant
        .fromEpochMilliseconds(created)
        .toLocalDateTime(TimeZone.currentSystemDefault()
        )
    )
}