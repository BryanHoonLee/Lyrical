package hoonstudio.com.tutory.RoomDB

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao){
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    // workerthread annotation marks this method needs to be called from a non-UI thread
    // suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function
    @WorkerThread
    suspend fun insert(user: User){
        userDao.insert(user)
    }

    @WorkerThread
    fun getUser(username: String) : User{
        return userDao.getUser(username)
    }

    @WorkerThread
    suspend fun updateUser(user: User){
        userDao.updateUserProfileInformation(user)
    }
}
