package hoonstudio.com.tutory.RoomDB

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

//A data class refers to a class that contains only fields and crude methods for accessing them (getters and setters).
// These are simply containers for data used by other classes.
// These classes do not contain any additional functionality and cannot independently operate on the data that they own.
@Entity(tableName = "user_table")
data class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long?,
                @ColumnInfo(name = "username") val username: String,
                @ColumnInfo(name = "email") val email : String,
                @ColumnInfo(name = "password") val password: String,
                @ColumnInfo(name = "address") var address : String?,
                @ColumnInfo(name = "state") var state : String?,
                @ColumnInfo(name = "city") var city : String?,
                @ColumnInfo(name = "zipcode") var zipcode: String?,
                @ColumnInfo(name = "phone_number") var phoneNumber: String?){
}