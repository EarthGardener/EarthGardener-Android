package team.gdsc.earthgardener.data.model.request.password

import com.google.gson.annotations.SerializedName

data class ReqModifyPasswordSuccessData(
    @SerializedName("ori_pw")
    val ori_pw : String,

    @SerializedName("new_pw")
    val new_pw: String
)
