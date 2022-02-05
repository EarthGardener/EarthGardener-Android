package team.gdsc.earthgardener.data.model.response


import com.google.gson.annotations.SerializedName

data class ResPostData(
    @SerializedName("data")
    val `data`: List<Data?>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("checklist")
        val checklist: List<String>,
        @SerializedName("date")
        val date: Int,
        @SerializedName("exp")
        val exp: Int,
        @SerializedName("title")
        val title: String
    )
}