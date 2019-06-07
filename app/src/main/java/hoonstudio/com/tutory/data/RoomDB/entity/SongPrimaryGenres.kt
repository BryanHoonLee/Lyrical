package hoonstudio.com.tutory.data.RoomDB.entity


import com.google.gson.annotations.SerializedName

data class SongPrimaryGenres(
    @SerializedName("music_genre_list")
    val musicGenreList: List<SongMusicGenre>
)