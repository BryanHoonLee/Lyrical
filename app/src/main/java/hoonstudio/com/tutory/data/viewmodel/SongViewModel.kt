package hoonstudio.com.tutory.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hoonstudio.com.tutory.data.network.SongNetworkDataSource
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.data.repository.SongRepository
import hoonstudio.com.tutory.data.repository.SongRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SongViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var songRepositoryImpl: SongRepositoryImpl



    init {
        songRepositoryImpl = SongRepositoryImpl(application)
    }

    suspend fun getSong(song: String): LiveData<SearchResponse>{
        return songRepositoryImpl.getSongFromNetwork(song)
    }
}