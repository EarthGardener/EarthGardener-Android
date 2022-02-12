package team.gdsc.earthgardener.data.model.request.signin

import com.google.gson.annotations.SerializedName

data class ReqSignInSuccessData(
    @SerializedName("email")
    val email: String,

    @SerializedName("pw")
    val pw: String
)
