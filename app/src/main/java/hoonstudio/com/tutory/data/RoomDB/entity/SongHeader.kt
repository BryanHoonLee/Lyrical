package hoonstudio.com.tutory.data.RoomDB.entity


import com.google.gson.annotations.SerializedName

data class SongHeader(
    @SerializedName("execute_time")
    val executeTime: Double,
    @SerializedName("status_code")
    val statusCode: Int
)