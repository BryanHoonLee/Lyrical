package hoonstudio.com.tutory.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.internal.NoConnectivityException

class SongNetworkDataSourceImpl(
    private val geniusApiService: GeniusApiService
) : SongNetworkDataSource {

    private val _song = MutableLiveData<SearchResponse>()

    override val song: LiveData<SearchResponse>
        get() = _song

    override suspend fun fetchSong(songName: String) {
        try{
            val fetchedSong = geniusApiService
                .getSong(songName)
                .await()
            _song.postValue(fetchedSong)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection")
        }
    }
}