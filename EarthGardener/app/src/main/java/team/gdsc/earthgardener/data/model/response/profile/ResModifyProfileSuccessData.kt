package team.gdsc.earthgardener.data.model.response.profile

import com.google.gson.annotations.SerializedName

data class ResModifyProfileSuccessData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
