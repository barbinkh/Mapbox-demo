package com.techs.mapapp.di

import com.mapbox.geojson.Feature
import com.techs.domain.common.Mapper
import com.techs.domain.entities.PinEntity
import com.techs.domain.usecases.GetPinDetails
import com.techs.mapapp.common.ASyncTransformer
import com.techs.mapapp.mappers.FeatureMapPinMapper
import com.techs.mapapp.mappers.PinEntityMapFeatureMapper
import org.koin.dsl.module

val pinModule = module {
    factory { GetPinDetails(ASyncTransformer(), get()) }
    factory { FeatureMapPinMapper() }
    factory<Mapper<PinEntity, Feature>> { PinEntityMapFeatureMapper() }
}