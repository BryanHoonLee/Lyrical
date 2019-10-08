package hoonstudio.com.tutory.data.repository

import androidx.lifecycle.LiveData
import hoonstudio.com.tutory.data.network.response.SearchResponse

interface SongRepository {

    suspend fun getSongFromNetwork(song: String): SearchResponse
}