package hoonstudio.com.tutory.data.roomdb.entity


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PrimaryArtistDb(
    val id: Int,
    @ColumnInfo(name="api_path")
    val apiPath: String,
    @ColumnInfo(name="header_image_url")
    val headerImageUrl: String,
    @ColumnInfo(name="image_url")
    val imageUrl: String,
    @ColumnInfo(name="is_meme_verified")
    val isMemeVerified: Boolean,
    @ColumnInfo(name="is_verified")
    val isVerified: Boolean,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="url")
    val url: String
)