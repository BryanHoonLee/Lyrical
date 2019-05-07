package hoonstudio.com.tutory.data.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao{

    @Query("SELECT * from user_table")
    fun getAllUsers(): LiveData<List<User>>

    @Insert
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table WHERE username = :username")
    fun getUser(username: String): User

    @Update
    fun updateUserProfileInformation(user: User)
}