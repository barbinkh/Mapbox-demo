package com.techs.mapapp.map

import com.mapbox.geojson.Feature
import com.techs.mapapp.entities.Pin

data class MapViewState(
    var showLoading: Boolean = true,
    var pins: List<Feature>? = null,
    var pin: Pin? = null
)