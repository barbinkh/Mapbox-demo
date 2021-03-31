package com.techs.data.repo

import com.techs.data.api.ApiService
import com.techs.data.mappers.PinDataEntityMapper
import com.techs.domain.entities.PinEntity
import com.techs.domain.repo.PinRepository
import io.reactivex.rxjava3.core.Observable

class PinRepositoryImpl(private val api: ApiService) : PinRepository {

    private val pinDataMapper = PinDataEntityMapper()

    override fun getPins(): Observable<List<PinEntity>> {
        return api.getPins(appMode = "swh-mein-halle-mobil").map { results ->
            results
                .filter { pinData -> pinData.type == "SimplePoi" }
                .map { pinDataMapper.mapFrom(it) }
        }
    }
}