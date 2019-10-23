package hoonstudio.com.tutory.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import hoonstudio.com.tutory.data.network.SongNetworkDataSource
import hoonstudio.com.tutory.data.network.SongNetworkDataSourceImpl
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.data.roomdb.SongDao
import hoonstudio.com.tutory.data.roomdb.SongDatabase
import hoonstudio.com.tutory.data.roomdb.entity.Search
import hoonstudio.com.tutory.data.roomdb.entity.Song

//Whenever the Repository has to do expensive things like transform a list it should use withContext to expose a main-safe interface.
class SongRepositoryImpl : SongRepository {
    private val songNetworkDataSource: SongNetworkDataSource
    private val songDao: SongDao

    constructor(application: Application) {
        val database = SongDatabase.getInstance(application)!!
        songDao = database.songDao()
        songNetworkDataSource = SongNetworkDataSourceImpl(application)
    }

    suspend fun insertSong(song: Song) {
        songDao.insert(song)
    }

    suspend fun insertSearchHistory(search: Search){
        songDao.insertSearch(search)
    }

    suspend fun getSearchHistoryFromDb(): List<Search>{
        return songDao.getSearchHistory()
    }

     fun getAllSongFromDb(): LiveData<List<Song>> {
        return songDao.getAllSong()
    }

    suspend fun deleteSong(song: Song){
        songDao.deleteSong(song)
    }


    override suspend fun getSongFromNetwork(song: String): SearchResponse {
        return songNetworkDataSource.fetchSong(song)
    }


}