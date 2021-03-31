package com.techs.mapapp.mappers

import com.google.gson.Gson
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.techs.domain.common.Mapper
import com.techs.domain.entities.PinEntity

class PinEntityMapSymbolMapper : Mapper<PinEntity, SymbolOptions>() {
    private val MAKI_ICON_CAR = "сar"
    override fun mapFrom(from: PinEntity): SymbolOptions {
        return SymbolOptions().withLatLng(LatLng(from.position[1], from.position[0]))
            .withIconImage(MAKI_ICON_CAR)
            .withDraggable(false)
            .withData(Gson().toJsonTree(from))
    }

}