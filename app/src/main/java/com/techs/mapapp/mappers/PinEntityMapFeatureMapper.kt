package com.techs.mapapp.mappers


import android.text.TextUtils
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.techs.domain.common.Mapper
import com.techs.domain.entities.PinEntity

class PinEntityMapFeatureMapper : Mapper<PinEntity, Feature>() {
    override fun mapFrom(from: PinEntity): Feature {
        val feature = Feature.fromGeometry(Point.fromLngLat(from.position[0], from.position[1]))
        feature.addNumberProperty("id", from.id)
        feature.addStringProperty("icon", from.icon ?: "")
        feature.addStringProperty("title", from.title ?: "")
        feature.addStringProperty("titleEn", from.titleEn ?: "")
        feature.addStringProperty("subtitle", from.subtitle ?: "")
        feature.addStringProperty("subtitleEn", from.subtitleEn ?: "")
        feature.addStringProperty("description", checkDescription(from.description ?: ""))
        feature.addStringProperty("descriptionEn", checkDescription(from.descriptionEn ?: ""))
        feature.addStringProperty("createdAt", from.createdAt ?: "")
        feature.addStringProperty("updatedAt", from.updatedAt ?: "")
        feature.addStringProperty("type", from.type)
        return feature
    }

    private fun checkDescription(description: String): String {
        return if (TextUtils.isEmpty(description)) "No description ${
            (String(
                Character.toChars(
                    0x1F61E
                )
            ))
        }"
        else description
    }
}