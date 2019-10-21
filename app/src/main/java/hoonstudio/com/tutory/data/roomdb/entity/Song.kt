package hoonstudio.com.tutory.data.roomdb.entity


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hoonstudio.com.tutory.data.network.response.PrimaryArtist

@Entity(tableName = "song_table")
data class Song(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Long,
    @Embedded(prefix = "primary_artist_")
    val primaryArtist: PrimaryArtist,
    @ColumnInfo(name ="title")
    val title: String,
    @ColumnInfo(name ="url")
    val url: String,
    @ColumnInfo(name = "audio_name")
    val audioName:String,
    @ColumnInfo(name = "file_path")
    val filePath: String,
    @ColumnInfo(name ="api_path")
    val apiPath: String,
    @ColumnInfo(name ="full_title")
    val fullTitle: String,
    @ColumnInfo(name ="header_image_thumbnail_url")
    val headerImageThumbnailUrl: String,
    @ColumnInfo(name ="header_image_url")
    val headerImageUrl: String,
    @ColumnInfo(name ="lyrics_owner_id")
    val lyricsOwnerId: Int,
    @ColumnInfo(name ="song_art_image_thumbnail_url")
    val songArtImageThumbnailUrl: String,
    @ColumnInfo(name ="song_art_image_url")
    val songArtImageUrl: String,
    @ColumnInfo(name ="title_with_featured")
    val titleWithFeatured: String
)