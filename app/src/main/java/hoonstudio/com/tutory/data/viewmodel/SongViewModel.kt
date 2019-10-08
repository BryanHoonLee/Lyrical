package hoonstudio.com.tutory.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import hoonstudio.com.tutory.data.network.SongNetworkDataSource
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.data.repository.SongRepository
import hoonstudio.com.tutory.data.repository.SongRepositoryImpl
import hoonstudio.com.tutory.data.roomdb.entity.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SongViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var songRepositoryImpl: SongRepositoryImpl

    // Result returned when user searches for a song
    private val _songQuery = MutableLiveData<SearchResponse>()
    val songQuery: LiveData<SearchResponse> = _songQuery

    // Song Recording List from Db
    private val _songList = MutableLiveData<List<Song>>()
    val songList: LiveData<List<Song>> = _songList

    init {
        songRepositoryImpl = SongRepositoryImpl(application)
    }

    fun insertSong(song: Song){
        viewModelScope.launch {
            songRepositoryImpl.insertSong(song)
        }
    }

    fun getAllSongFromDb(){
        viewModelScope.launch {
            _songList.value = songRepositoryImpl.getAllSongFromDb()
        }
    }

    fun getSong(song: String){
        viewModelScope.launch {
            _songQuery.value = songRepositoryImpl.getSongFromNetwork(song)

        }
    }
}