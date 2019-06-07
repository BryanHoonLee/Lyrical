package hoonstudio.com.tutory.data.RoomDB.entity

import androidx.room.Embedded


data class Body(
    @Embedded(prefix = "lyrics_")
    val lyrics: LyricEntry
)