package com.techs.data.mappers

import com.techs.data.entities.PinData
import com.techs.domain.common.Mapper
import com.techs.domain.entities.PinEntity

class PinDataEntityMapper : Mapper<PinData, PinEntity>() {
    override fun mapFrom(from: PinData): PinEntity {
        return PinEntity(
            id = from.id,
            icon = from.icon,
            title = from.title,
            titleEn = from.titleEn,
            subtitle = from.subtitle,
            subtitleEn = from.subtitleEn,
            description = from.description,
            descriptionEn = from.descriptionEn,
            position = from.position,
            createdAt = from.createdAt,
            updatedAt = from.updatedAt,
            type = from.type
        )
    }
}