package hoonstudio.com.tutory.data.RoomDB.entity

import androidx.room.Embedded


data class LyricBody(
    @Embedded(prefix = "lyrics_")
    val lyrics: LyricEntry
)