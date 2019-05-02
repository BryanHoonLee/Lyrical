package hoonstudio.com.tutory.RoomDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class UserViewModel(application: Application) : AndroidViewModel(application){

    private var parentJob = Job()

    // The coroutineContext, by default, uses the parentJob and the main dispatcher to create a
    // new instance of a CoroutineScope based on the coroutineContext
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = UserRoomDataBase.getDatabase(application,scope).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    override fun onCleared(){
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(user: User) = scope.launch(Dispatchers.IO){
        repository.insert(user)
    }

    fun getUser(username: String) : User{
        return repository.getUser(username)
    }

    fun updateUser(user: User) = scope.launch(Dispatchers.IO){
        repository.updateUser(user)
    }
}