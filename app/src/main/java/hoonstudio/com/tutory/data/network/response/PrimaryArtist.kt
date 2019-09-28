package hoonstudio.com.tutory.data.network.response


import com.google.gson.annotations.SerializedName

data class PrimaryArtist(
    @SerializedName("api_path")
    val apiPath: String,
    @SerializedName("header_image_url")
    val headerImageUrl: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_meme_verified")
    val isMemeVerified: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    val name: String,
    val url: String
)