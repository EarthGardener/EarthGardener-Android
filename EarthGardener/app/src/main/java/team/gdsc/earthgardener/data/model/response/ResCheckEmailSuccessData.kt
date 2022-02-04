package team.gdsc.earthgardener.data.model.response

import com.google.gson.annotations.SerializedName

data class ResCheckEmailSuccessData(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
