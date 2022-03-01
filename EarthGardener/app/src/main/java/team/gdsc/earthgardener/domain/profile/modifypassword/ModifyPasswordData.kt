package team.gdsc.earthgardener.domain.profile.modifypassword

import com.google.gson.annotations.SerializedName

data class ModifyPasswordData(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)
