package team.gdsc.earthgardener.data.model.response.password

import com.google.gson.annotations.SerializedName

data class ResModifyPasswordSuccessData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
