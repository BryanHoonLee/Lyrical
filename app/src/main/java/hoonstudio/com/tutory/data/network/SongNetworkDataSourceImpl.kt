package hoonstudio.com.tutory.data.network

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.internal.NoConnectivityException

class SongNetworkDataSourceImpl(
    application: Application
) : SongNetworkDataSource {
    private val geniusApiService: GeniusApiService

    private val _song = MutableLiveData<SearchResponse>()
    override val song: LiveData<SearchResponse>
        get() = _song

    init{
        geniusApiService = GeniusApiService(ConnectivityInterceptorImpl(application.applicationContext))
    }


    override suspend fun fetchSong(songName: String): SearchResponse {
        try{
            val fetchedSong = geniusApiService
                .getSong(songName)
                .await()

            _song.value = fetchedSong

        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection")
        }
        return song.value!!
    }
}