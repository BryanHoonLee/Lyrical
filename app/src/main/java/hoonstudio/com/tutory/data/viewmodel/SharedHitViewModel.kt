package hoonstudio.com.tutory.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hoonstudio.com.tutory.data.network.response.Hit

class SharedHitViewModel: ViewModel(){
    val _sharedHit = MutableLiveData<Hit>()
    val sharedHit: LiveData<Hit> = _sharedHit

    fun setSharedSong(hit: Hit){
        _sharedHit.value = hit
    }

}