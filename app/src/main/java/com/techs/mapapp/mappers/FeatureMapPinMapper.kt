package com.techs.mapapp.mappers

import com.mapbox.geojson.Feature
import com.techs.domain.common.Mapper
import com.techs.mapapp.entities.Pin

class FeatureMapPinMapper : Mapper<Feature, Pin>() {
    override fun mapFrom(from: Feature): Pin {
        return Pin(
            id = from.getNumberProperty("id").toInt(),
            icon = from.getStringProperty("icon"),
            title = from.getStringProperty("title"),
            titleEn = from.getStringProperty("titleEn"),
            subtitle = from.getStringProperty("subtitle"),
            subtitleEn = from.getStringProperty("subtitleEn"),
            description = from.getStringProperty("description"),
            descriptionEn = from.getStringProperty("descriptionEn"),
            createdAt = from.getStringProperty("createdAt"),
            updatedAt = from.getStringProperty("updatedAt"),
            type = from.getStringProperty("type")
        )
    }
}