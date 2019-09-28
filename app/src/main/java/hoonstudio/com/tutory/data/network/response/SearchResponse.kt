package hoonstudio.com.tutory.data.network.response


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val meta: Meta,
    val response: Response
)