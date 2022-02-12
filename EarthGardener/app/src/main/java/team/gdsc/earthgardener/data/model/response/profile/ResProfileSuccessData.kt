package team.gdsc.earthgardener.data.model.response.profile

import com.google.gson.annotations.SerializedName

data class ResProfileSuccessData(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Profile,

    @SerializedName("status")
    val status: Int
){
    data class Profile(
        @SerializedName("email")
        val email: String,

        @SerializedName("nickname")
        val nickname: String,

        @SerializedName("image_url")
        val image_url: String
    )
}
