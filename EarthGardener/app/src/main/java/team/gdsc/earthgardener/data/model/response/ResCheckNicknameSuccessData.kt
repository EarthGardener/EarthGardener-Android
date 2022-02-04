package team.gdsc.earthgardener.data.model.response

import com.google.gson.annotations.SerializedName

data class ResCheckNicknameSuccessData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
