package com.techs.mapapp.map

import androidx.lifecycle.MutableLiveData
import com.mapbox.geojson.Feature
import com.techs.domain.common.Mapper
import com.techs.domain.entities.PinEntity
import com.techs.domain.usecases.GetPinDetails
import com.techs.mapapp.common.BaseViewModel
import com.techs.mapapp.common.SingleLiveEvent
import com.techs.mapapp.mappers.FeatureMapPinMapper

open class MapViewModel(
    private val getPinDetails: GetPinDetails,
    private val featureMapPinMapper: FeatureMapPinMapper,
    private val pinEntityMapFeatureMapper: Mapper<PinEntity, Feature>

) : BaseViewModel() {
    var viewState: MutableLiveData<MapViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = MapViewState()
    }

    fun getPinsDetails() {
        addDisposable(
            getPinDetails
                .observable()
                .flatMap { pinEntityMapFeatureMapper.observable(it) }
                .subscribe({ pins ->
                    viewState.value?.let {
                        val newState = this.viewState.value?.copy(showLoading = false, pins = pins)
                        this.viewState.value = newState
                        this.errorState.value = null
                    }
                },
                    {
                        viewState.value = viewState.value?.copy(showLoading = false)
                        errorState.value = it
                    })
        )
    }

    fun getPinInfo(feature: Feature) {
        featureMapPinMapper.observable(feature)
            .subscribe({
                val newState = this.viewState.value?.copy(pin = it)
                this.viewState.value = newState
                this.errorState.value = null
            }, { errorState.value = it })
    }
}