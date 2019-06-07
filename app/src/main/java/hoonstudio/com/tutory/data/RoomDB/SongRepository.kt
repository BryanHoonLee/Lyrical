package hoonstudio.com.tutory.data.RoomDB

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class SongRepository(private val songDao: SongDao) {
    val allSongs: LiveData<List<Song>> = songDao.getAllSongs()

    @WorkerThread
    suspend fun insert(song: Song){
        songDao.insertSong(song)
    }


}