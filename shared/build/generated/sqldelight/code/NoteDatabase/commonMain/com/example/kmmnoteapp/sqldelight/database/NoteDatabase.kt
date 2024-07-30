package com.example.kmmnoteapp.sqldelight.database

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.example.kmmnoteapp.sqldelight.database.shared.newInstance
import com.example.kmmnoteapp.sqldelight.database.shared.schema
import database.NoteQueries
import kotlin.Unit

public interface NoteDatabase : Transacter {
  public val noteQueries: NoteQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = NoteDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): NoteDatabase =
        NoteDatabase::class.newInstance(driver)
  }
}
