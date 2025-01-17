package database

import kotlin.Long
import kotlin.String

public data class NoteEntity(
  public val id: Long,
  public val title: String,
  public val content: String,
  public val colorHex: Long,
  public val created: Long,
)
