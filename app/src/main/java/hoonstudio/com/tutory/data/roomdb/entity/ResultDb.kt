package hoonstudio.com.tutory.data.roomdb.entity


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import hoonstudio.com.tutory.data.network.response.PrimaryArtist

@Entity(tableName = "result_table")
data class ResultDb(
    val id: Int,
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
    @ColumnInfo(name ="lyrics_owner_id")
    val lyricsOwnerId: Int,
    @Embedded(prefix = "primary_artist_")
    @ColumnInfo(name ="primary_artist")
    val primaryArtist: PrimaryArtist,
    @ColumnInfo(name ="song_art_image_thumbnail_url")
    val songArtImageThumbnailUrl: String,
    @ColumnInfo(name ="song_art_image_url")
    val songArtImageUrl: String,
    @ColumnInfo(name ="title")
    val title: String,
    @ColumnInfo(name ="title_with_featured")
    val titleWithFeatured: String,
    @ColumnInfo(name ="url")
    val url: String
)