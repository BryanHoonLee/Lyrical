package hoonstudio.com.tutory.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import hoonstudio.com.tutory.data.RoomDB.SongDao
import hoonstudio.com.tutory.data.network.SongNetworkDataSource
import hoonstudio.com.tutory.data.network.SongNetworkDataSourceImpl
import hoonstudio.com.tutory.data.network.response.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongRepositoryImpl(
    private val application: Application

) : SongRepository {
    private val songNetworkDataSource: SongNetworkDataSource
    //private val songDao:SongDao

    init {
        songNetworkDataSource = SongNetworkDataSourceImpl(application)
    }

    override suspend fun getSongFromNetwork(song: String): LiveData<SearchResponse> {
        return songNetworkDataSource.fetchSong(song)

    }


}