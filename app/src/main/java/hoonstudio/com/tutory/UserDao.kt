package hoonstudio.com.tutory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao{

    @Query("SELECT * from user_table ORDER BY user ASC")
    fun getAllUsers(): List<User>

    @Insert
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}