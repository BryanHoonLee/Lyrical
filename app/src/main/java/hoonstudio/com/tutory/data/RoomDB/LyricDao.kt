package hoonstudio.com.tutory.data.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hoonstudio.com.tutory.data.RoomDB.entity.LyricEntry

@Dao
interface LyricDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(lyricEntry: LyricEntry)

    @Query("select * from lyric_table")
    fun getLyrics(): LiveData<LyricEntry>
}