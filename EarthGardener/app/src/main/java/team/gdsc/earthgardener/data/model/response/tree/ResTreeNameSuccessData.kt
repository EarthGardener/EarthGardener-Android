package team.gdsc.earthgardener.data.model.response.tree

import com.google.gson.annotations.SerializedName

data class ResTreeNameSuccessData(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)
