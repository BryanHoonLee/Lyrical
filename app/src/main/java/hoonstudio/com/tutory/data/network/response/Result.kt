package hoonstudio.com.tutory.data.network.response


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("annotation_count")
    val annotationCount: Int,
    @SerializedName("api_path")
    val apiPath: String,
    @SerializedName("full_title")
    val fullTitle: String,
    @SerializedName("header_image_thumbnail_url")
    val headerImageThumbnailUrl: String,
    @SerializedName("header_image_url")
    val headerImageUrl: String,
    val id: Int,
    @SerializedName("lyrics_owner_id")
    val lyricsOwnerId: Int,
    @SerializedName("lyrics_state")
    val lyricsState: String,
    val path: String,
    @Embedded(prefix = "primary_artist_")
    @SerializedName("primary_artist")
    val primaryArtist: PrimaryArtist,
//    @SerializedName("pyongs_count")
//    val pyongsCount: Any,
    @SerializedName("song_art_image_thumbnail_url")
    val songArtImageThumbnailUrl: String,
    @SerializedName("song_art_image_url")
    val songArtImageUrl: String,
//    val stats: Stats,
    val title: String,
    @SerializedName("title_with_featured")
    val titleWithFeatured: String,
    val url: String
)