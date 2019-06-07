package hoonstudio.com.tutory.data.RoomDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SongViewModel(application: Application): AndroidViewModel(application) {

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: SongRepository
    val allSongs : LiveData<List<Song>>

    init{
        val songDao = SongRoomDataBase.getDatabase(application, scope).songDao()
        repository = SongRepository(songDao)
        allSongs = repository.allSongs
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(song:Song) = scope.launch(Dispatchers.IO){
        repository.insert(song)
    }



}