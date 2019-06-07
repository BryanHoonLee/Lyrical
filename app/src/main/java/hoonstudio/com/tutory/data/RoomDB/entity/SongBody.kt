package hoonstudio.com.tutory.data.RoomDB.entity

import androidx.room.Embedded


data class SongBody(
    @Embedded(prefix = "track_")
    val track: SongTrack
)