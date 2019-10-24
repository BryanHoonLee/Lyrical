package hoonstudio.com.tutory.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hoonstudio.com.tutory.data.roomdb.entity.Song

class SharedRecordingViewModel: ViewModel(){
    private val _sharedRecording = MutableLiveData<Song>()
    val sharedRecording = _sharedRecording

    fun setSharedRecording(song: Song){
        _sharedRecording.value = song
    }
}