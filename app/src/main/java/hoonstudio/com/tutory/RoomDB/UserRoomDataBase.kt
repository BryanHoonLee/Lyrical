package hoonstudio.com.tutory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserRoomDataBase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDataBase? = null

        fun getDatabase(context: Context): UserRoomDataBase{
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDataBase::class.java,
                    "User_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}