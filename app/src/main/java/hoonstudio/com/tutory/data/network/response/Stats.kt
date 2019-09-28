package hoonstudio.com.tutory.data.network.response


import com.google.gson.annotations.SerializedName

data class Stats(
    val hot: Boolean,
    val pageviews: Int,
    @SerializedName("unreviewed_annotations")
    val unreviewedAnnotations: Int
)