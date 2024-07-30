package com.example.kmmnoteapp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.kmmnoteapp.sqldelight.database.NoteDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = NoteDatabase.Schema,
            name = "note.db",
        )
    }
}