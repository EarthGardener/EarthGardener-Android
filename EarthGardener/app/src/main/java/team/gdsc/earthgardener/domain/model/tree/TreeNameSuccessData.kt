package team.gdsc.earthgardener.domain.model.tree

import com.google.gson.annotations.SerializedName

data class TreeNameSuccessData (
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int
)