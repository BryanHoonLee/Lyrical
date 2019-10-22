package hoonstudio.com.tutory.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hoonstudio.com.tutory.data.roomdb.entity.Search
import hoonstudio.com.tutory.data.roomdb.entity.Song

@Dao
interface SongDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: Search)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(result: Song)

    @Query("SELECT * FROM search_table ORDER BY time_stamp DESC limit 10")
    suspend fun getSearchHistory(): List<Search>

    @Query("SELECT * FROM song_table")
    suspend fun getAllSong(): List<Song>



}