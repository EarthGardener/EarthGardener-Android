package team.gdsc.earthgardener.data.model.response.tree

import com.google.gson.annotations.SerializedName

data class ResTreeInfoSuccessData(
    @SerializedName("data")
    val data: Tree,
    @SerializedName("levelup")
    val isLevelUp: Boolean,
    @SerializedName("user_nickname")
    val userNickName: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) {
    data class Tree(
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
