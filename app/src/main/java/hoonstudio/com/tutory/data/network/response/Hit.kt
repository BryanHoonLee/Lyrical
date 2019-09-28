package hoonstudio.com.tutory.data.network.response


import com.google.gson.annotations.SerializedName

data class Hit(
    val highlights: List<Any>,
    val index: String,
    val result: Result,
    val type: String
)