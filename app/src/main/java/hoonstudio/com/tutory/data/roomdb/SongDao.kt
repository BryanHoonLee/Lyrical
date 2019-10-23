package hoonstudio.com.tutory.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
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
    fun getAllSong(): LiveData<List<Song>>

    @Delete
    suspend fun deleteSong(song: Song)



}