package hoonstudio.com.tutory.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hoonstudio.com.tutory.data.repository.SongRepositoryImpl
import hoonstudio.com.tutory.data.roomdb.entity.Search
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var songRepositoryImpl: SongRepositoryImpl

    private val _searchHistoryList = MutableLiveData<List<Search>>()
    val searchHistoryList: LiveData<List<Search>> = _searchHistoryList

    init {
        songRepositoryImpl = SongRepositoryImpl(application)
    }

    fun getSearchHistoryFromDb(){
        viewModelScope.launch {
            _searchHistoryList.value = songRepositoryImpl.getSearchHistoryFromDb()
        }
    }

    fun insertSearch(search: Search){
        viewModelScope.launch {
            songRepositoryImpl.insertSearchHistory(search)
        }
    }

}