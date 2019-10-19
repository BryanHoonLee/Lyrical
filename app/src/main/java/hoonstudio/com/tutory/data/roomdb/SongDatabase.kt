package hoonstudio.com.tutory.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import hoonstudio.com.tutory.data.roomdb.entity.PrimaryArtistDb
import hoonstudio.com.tutory.data.roomdb.entity.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Song::class), version = 2)
abstract class SongDatabase: RoomDatabase(){

    companion object{

        private lateinit var instance: SongDatabase

        fun getInstance(context: Context): SongDatabase?{
            if(!this::instance.isInitialized){
                synchronized(SongDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        SongDatabase::class.java, "song.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        // returns a value of anonymous type object that is a slight modification of RoomDatabase.Callback()
        // without having to create a subclass for it.
        // .Callback() is an inner class of RoomDatabase hence the .
        // having a value = object [...] {} will assign the end value of 'object' to the roomCallback variable.
        private val roomCallback = object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDb(instance).initDb()
            }
        }

        private class PopulateDb internal constructor(private val songDatabase: SongDatabase){
            private val scope = CoroutineScope(Dispatchers.Default)
            private val songDao = songDatabase.songDao()

            fun initDb() = scope.launch {
                //init database with songs if needed
            }
        }
    }

    abstract fun songDao(): SongDao
}