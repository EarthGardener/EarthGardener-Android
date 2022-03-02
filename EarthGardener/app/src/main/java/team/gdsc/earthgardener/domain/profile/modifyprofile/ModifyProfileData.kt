package team.gdsc.earthgardener.domain.profile.modifyprofile

import com.google.gson.annotations.SerializedName

data class ModifyProfileData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
