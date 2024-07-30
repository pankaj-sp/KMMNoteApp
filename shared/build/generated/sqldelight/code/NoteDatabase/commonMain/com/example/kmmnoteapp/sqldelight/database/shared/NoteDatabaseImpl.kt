package com.example.kmmnoteapp.sqldelight.database.shared

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.example.kmmnoteapp.sqldelight.database.NoteDatabase
import database.NoteQueries
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<NoteDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = NoteDatabaseImpl.Schema

internal fun KClass<NoteDatabase>.newInstance(driver: SqlDriver): NoteDatabase =
    NoteDatabaseImpl(driver)

private class NoteDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), NoteDatabase {
  override val noteQueries: NoteQueries = NoteQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE noteEntity(
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    title TEXT NOT NULL,
          |    content TEXT NOT NULL,
          |    colorHex INTEGER NOT NULL,
          |    created INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
