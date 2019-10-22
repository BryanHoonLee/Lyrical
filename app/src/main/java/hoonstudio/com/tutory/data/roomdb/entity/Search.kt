package hoonstudio.com.tutory.data.roomdb.entity


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hoonstudio.com.tutory.data.network.response.PrimaryArtist

@Entity(tableName = "search_table")
data class Search(
    @ColumnInfo(name ="annotation_count")
    val annotationCount: Int,
    @ColumnInfo(name ="api_path")
    val apiPath: String,
    @ColumnInfo(name ="full_title")
    val fullTitle: String,
    @ColumnInfo(name ="header_image_thumbnail_url")
    val headerImageThumbnailUrl: String,
    @ColumnInfo(name ="header_image_url")
    val headerImageUrl: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long,
    @ColumnInfo(name ="lyrics_owner_id")
    val lyricsOwnerId: Int,
    @ColumnInfo(name ="lyrics_state")
    val lyricsState: String,
    val path: String,
    @Embedded(prefix = "primary_artist_")
    val primaryArtist: PrimaryArtist,
    @ColumnInfo(name ="song_art_image_thumbnail_url")
    val songArtImageThumbnailUrl: String,
    @ColumnInfo(name ="song_art_image_url")
    val songArtImageUrl: String,
    val title: String,
    @ColumnInfo(name ="title_with_featured")
    val titleWithFeatured: String,
    val url: String
)