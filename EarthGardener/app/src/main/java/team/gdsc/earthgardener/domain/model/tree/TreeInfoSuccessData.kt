package team.gdsc.earthgardener.domain.model.tree

import com.google.gson.annotations.SerializedName

data class TreeInfoSuccessData(
    val data: TreeInfo,
    @SerializedName("levelup")
    val levelUp: Boolean,
    @SerializedName("user_nickname")
    val userNickName: String,
) {
    data class TreeInfo (
        @SerializedName("name")
        val name: String,
        @SerializedName("level")
        val level: Int,
        @SerializedName("exp")
        val exp: Int,
        @SerializedName("total_sum")
        val totalSum: Int,
        @SerializedName("month_sum")
        val monthSum: Int
    )

}
