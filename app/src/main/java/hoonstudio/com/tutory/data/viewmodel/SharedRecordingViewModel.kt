package hoonstudio.com.tutory.data.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hoonstudio.com.tutory.data.roomdb.entity.Song

class SharedRecordingViewModel: ViewModel(){
    private val _sharedRecording = MutableLiveData<Song>()
    val sharedRecording = _sharedRecording

    private val _mediaPlayer = MutableLiveData<MediaPlayer>()
    val mediaPlayer = _mediaPlayer

    private val _progress = MutableLiveData<Int>()
    val progress = _progress

    fun setMediaPlayer(mediaPlayer: MediaPlayer){
        _mediaPlayer.value = mediaPlayer
    }

    fun setSharedRecording(song: Song){
        _sharedRecording.value = song
    }

    fun setProgress(progress: Int){
        _progress.value = progress
    }
}