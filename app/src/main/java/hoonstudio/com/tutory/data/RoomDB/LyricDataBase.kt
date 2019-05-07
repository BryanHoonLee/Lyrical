package hoonstudio.com.tutory.data.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hoonstudio.com.tutory.data.RoomDB.entity.LyricEntry

@Database(
    entities = [LyricEntry::class],
    version = 1
)
// the Lyric database class will implement the LyricDao
abstract class LyricDataBase : RoomDatabase(){
    abstract fun lyricDao(): LyricDao

    companion object{
        //volatile gives immediate access to all threads
        @Volatile private var instance: LyricDataBase? = null
        // Lock object is used when we have multiple threads.
        // Doesn't matter what type the Lock object is.
        // It's a dummy object to make sure no 2 threads are currently doing the same thing
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            // if instance is already initialized then return, otherwise execute code after the ?:
            // 'also' whatever is returned from buildDatabase, we want instance to be equal to 'it'
            instance ?: buildDatabase(context).also { instance = it }
        }

        // "buildDatabase() = ..." just means return whatever is after the '='
        private fun buildDatabase(context: Context) =
            // context.applicationContext so if the given context is something like a fragment context, you'll end up
            // with the context of the whole application instead
                Room.databaseBuilder(context.applicationContext,
                    LyricDataBase::class.java, "Lyric_database")
                    .build()
    }
}