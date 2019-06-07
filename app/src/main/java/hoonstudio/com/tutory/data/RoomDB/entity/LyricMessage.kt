package hoonstudio.com.tutory.data.RoomDB.entity

import androidx.room.Embedded


data class LyricMessage(
    @Embedded(prefix = "body_")
    val body: LyricBody,
    @Embedded(prefix = "header_")
    val header: LyricHeader
)