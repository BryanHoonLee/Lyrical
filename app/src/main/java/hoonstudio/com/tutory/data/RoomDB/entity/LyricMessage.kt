package hoonstudio.com.tutory.data.RoomDB.entity

import androidx.room.Embedded


data class Message(
    @Embedded(prefix = "body_")
    val body: Body,
    @Embedded(prefix = "header_")
    val header: Header
)