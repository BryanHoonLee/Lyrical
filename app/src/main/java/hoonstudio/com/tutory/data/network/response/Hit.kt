package hoonstudio.com.tutory.data.network.response


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Hit(
    val result: Result

//    val highlights: List<Any>,
//    val index: String,
//    val type: String
)