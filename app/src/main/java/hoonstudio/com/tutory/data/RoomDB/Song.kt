package hoonstudio.com.tutory.data.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_table")
data class Song(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                @ColumnInfo(name = "song_name") val songName: String,
                @ColumnInfo(name = "song_lyrics") val songLyrics: String) {

}