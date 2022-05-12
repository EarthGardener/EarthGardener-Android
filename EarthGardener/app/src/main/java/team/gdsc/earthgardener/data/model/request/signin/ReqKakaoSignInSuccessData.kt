package team.gdsc.earthgardener.data.model.request.signin

import com.google.gson.annotations.SerializedName

data class ReqKakaoSignInSuccessData(
    @SerializedName("kakao_id")
    val kakao_id: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("image_url")
    val image_url : String?= null,

    @SerializedName("email")
    val email: String
)
