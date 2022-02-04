package team.gdsc.earthgardener.data.model.response


import com.google.gson.annotations.SerializedName

data class ResChecklistData(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("ment")
        val ment: String
    )
}