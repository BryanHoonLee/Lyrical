package hoonstudio.com.tutory.data.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@Database(entities = [Song::class], version = 1)
abstract class SongRoomDataBase : RoomDatabase(){
    abstract fun songDao(): SongDao

    companion object{
        @Volatile
        private var INSTANCE: SongRoomDataBase? = null

        fun getDatabase(context: Context, scope:CoroutineScope): SongRoomDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongRoomDataBase::class.java,
                    "Song_database"
                ).addCallback(SongDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class SongDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{ database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.songDao())
                }

            }
        }

        fun populateDatabase(songDao:SongDao){

        }
    }
}