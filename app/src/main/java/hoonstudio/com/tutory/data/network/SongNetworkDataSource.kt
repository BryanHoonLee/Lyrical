package hoonstudio.com.tutory.data.network

import androidx.lifecycle.LiveData
import hoonstudio.com.tutory.data.network.response.SearchResponse

interface SongNetworkDataSource {
    val song: LiveData<SearchResponse>

    suspend fun fetchSong(
        songName: String
    )
}