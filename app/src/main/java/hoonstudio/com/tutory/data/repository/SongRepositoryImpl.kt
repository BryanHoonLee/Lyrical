package hoonstudio.com.tutory.data.repository

import androidx.lifecycle.LiveData
import hoonstudio.com.tutory.data.RoomDB.SongDao
import hoonstudio.com.tutory.data.network.SongNetworkDataSource
import hoonstudio.com.tutory.data.network.response.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongRepositoryImpl(
    private val songDao: SongDao,
    private val songNetworkDataSource: SongNetworkDataSource
) : SongRepository {

    init {
        songNetworkDataSource.song.observeForever{newSong ->

        }
    }

    override suspend fun getSong(): LiveData<SearchResponse> {
        TODO()
    }

    private fun persistFetchedSong(fetchedSong: SearchResponse){

        GlobalScope.launch(Dispatchers.IO) {
            songDao
        }
    }
}