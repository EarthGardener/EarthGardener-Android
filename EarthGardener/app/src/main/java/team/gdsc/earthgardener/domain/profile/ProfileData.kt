package team.gdsc.earthgardener.domain.profile

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: ProfileInfo,

    @SerializedName("status")
    val status: Int
){
    data class ProfileInfo(
        @SerializedName("email")
        val email: String,

        @SerializedName("nickname")
        val nickname: String,

        @SerializedName("image_url")
        val image_url: String
    )
}
