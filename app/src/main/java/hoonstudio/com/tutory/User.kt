package hoonstudio.com.tutory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//To make the class meaningful to a Room database, you need to annotate it.
// Annotations identify how each part of this class relates to an entry in the database.
// Room uses this information to generate code.
//
//@Entity(tableName = "word_table")
//Each @Entity class represents an entity in a table. Annotate your class declaration to indicate that it's an entity.
// Specify the name of the table if you want it to be different from the name of the class.

//@PrimaryKey
//Every entity needs a primary key. To keep things simple, each word acts as its own primary key.

//@ColumnInfo(name = "word")
//Specify the name of the column in the table if you want it to be different from the name of the member variable.
//Every field that's stored in the database needs to be either public or have a "getter" method.
@Entity(tableName = "user_table")
class User(@PrimaryKey @ColumnInfo(name = "user") val word: String)