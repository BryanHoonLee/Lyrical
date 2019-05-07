package hoonstudio.com.tutory.data.response


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("execute_time")
    val executeTime: Double,
    @SerializedName("status_code")
    val statusCode: Int
)