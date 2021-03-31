package com.techs.domain.entities

data class PinEntity(
    var id: Int = 0,
    var icon: String?,
    var title: String?,
    var titleEn: String?,
    var subtitle: String?,
    var subtitleEn: String?,
    var description: String?,
    var descriptionEn: String?,
    var position: List<Double>,
    var createdAt: String?,
    var updatedAt: String?,
    var type: String
)
