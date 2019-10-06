package hoonstudio.com.tutory.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import hoonstudio.com.tutory.data.network.SongNetworkDataSource
import hoonstudio.com.tutory.data.network.SongNetworkDataSourceImpl
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.data.roomdb.SongDao
import hoonstudio.com.tutory.data.roomdb.SongDatabase
import hoonstudio.com.tutory.data.roomdb.entity.Song

class SongRepositoryImpl : SongRepository {
    private val songNetworkDataSource: SongNetworkDataSource
    private val songDao: SongDao
    private var allSongs: LiveData<List<Song>>

    constructor(application: Application){
        val database = SongDatabase.getInstance(application)!!
        songDao = database.songDao()
        allSongs = songDao.getAllSong()
        songNetworkDataSource = SongNetworkDataSourceImpl(application)
    }


    override suspend fun getSongFromNetwork(song: String): LiveData<SearchResponse> {
        return songNetworkDataSource.fetchSong(song)

    }


}