package com.techs.data.entities

import com.google.gson.annotations.SerializedName

data class PinData(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("icon")
    var icon: String?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("title_en")
    var titleEn: String?,

    @SerializedName("subtitle")
    var subtitle: String?,

    @SerializedName("subtitle_en")
    var subtitleEn: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("description_en")
    var descriptionEn: String?,

    @SerializedName("position")
    var position: List<Double>,

    @SerializedName("created_at")
    var createdAt: String?,

    @SerializedName("updated_at")
    var updatedAt: String?,

    @SerializedName("type")
    var type: String
)