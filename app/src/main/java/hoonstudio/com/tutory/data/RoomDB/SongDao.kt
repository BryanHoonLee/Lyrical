package hoonstudio.com.tutory.data.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDao{

    @Query("SELECT * from song_table")
    fun getAllSongs(): LiveData<List<Song>>

    @Insert
    fun insertSong(song:Song)

}