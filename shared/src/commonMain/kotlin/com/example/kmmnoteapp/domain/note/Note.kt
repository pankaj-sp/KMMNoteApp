package com.example.kmmnoteapp.domain.note

import com.example.kmmnoteapp.presentation.BabyBlueHex
import com.example.kmmnoteapp.presentation.LightGreenHex
import com.example.kmmnoteapp.presentation.RedOrangeHex
import com.example.kmmnoteapp.presentation.RedPinkHex
import com.example.kmmnoteapp.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content : String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}
