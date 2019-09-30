package hoonstudio.com.tutory.data.network.response


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_hits")
data class Hit(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long,
    @Embedded(prefix = "result_")
    val result: Result

//    val highlights: List<Any>,
//    val index: String,
//    val type: String
)