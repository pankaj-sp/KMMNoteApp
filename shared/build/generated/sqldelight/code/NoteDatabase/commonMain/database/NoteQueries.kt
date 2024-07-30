package database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class NoteQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAllNotes(mapper: (
    id: Long,
    title: String,
    content: String,
    colorHex: Long,
    created: Long,
  ) -> T): Query<T> = Query(-623_426_313, arrayOf("noteEntity"), driver, "note.sq", "getAllNotes",
      "SELECT noteEntity.id, noteEntity.title, noteEntity.content, noteEntity.colorHex, noteEntity.created FROM noteEntity") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun getAllNotes(): Query<NoteEntity> = getAllNotes { id, title, content, colorHex,
      created ->
    NoteEntity(
      id,
      title,
      content,
      colorHex,
      created
    )
  }

  public fun <T : Any> getNoteById(id: Long, mapper: (
    id: Long,
    title: String,
    content: String,
    colorHex: Long,
    created: Long,
  ) -> T): Query<T> = GetNoteByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun getNoteById(id: Long): Query<NoteEntity> = getNoteById(id) { id_, title, content,
      colorHex, created ->
    NoteEntity(
      id_,
      title,
      content,
      colorHex,
      created
    )
  }

  public fun insertNote(
    id: Long?,
    title: String,
    content: String,
    colorHex: Long,
    created: Long,
  ) {
    driver.execute(-1_395_401_654, """
        |INSERT OR REPLACE INTO noteEntity(
        |    id,
        |    title,
        |    content,
        |    colorHex,
        |    created
        |) VALUES (?, ?, ?, ?, ?)
        """.trimMargin(), 5) {
          bindLong(0, id)
          bindString(1, title)
          bindString(2, content)
          bindLong(3, colorHex)
          bindLong(4, created)
        }
    notifyQueries(-1_395_401_654) { emit ->
      emit("noteEntity")
    }
  }

  public fun deleteNoteById(id: Long) {
    driver.execute(1_652_369_454, """
        |DELETE FROM noteEntity
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindLong(0, id)
        }
    notifyQueries(1_652_369_454) { emit ->
      emit("noteEntity")
    }
  }

  private inner class GetNoteByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("noteEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("noteEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-825_247_173,
        """SELECT noteEntity.id, noteEntity.title, noteEntity.content, noteEntity.colorHex, noteEntity.created FROM noteEntity WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "note.sq:getNoteById"
  }
}
