package hoonstudio.com.tutory.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hoonstudio.com.tutory.data.roomdb.entity.Song

@Dao
interface SongDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(result: Song)

    @Query("SELECT * FROM song_table")
    suspend fun getAllSong(): List<Song>

}