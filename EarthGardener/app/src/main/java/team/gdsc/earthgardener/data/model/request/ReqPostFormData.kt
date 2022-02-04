package team.gdsc.earthgardener.data.model.request


import com.google.gson.annotations.SerializedName

data class ReqPostFormData(
    @SerializedName("checklist_1")
    val checklist1: Int,
    @SerializedName("checklist_2")
    val checklist2: Int?,
    @SerializedName("checklist_3")
    val checklist3: Int?,
    @SerializedName("title")
    val title: String
)