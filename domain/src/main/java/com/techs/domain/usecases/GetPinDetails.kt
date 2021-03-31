package com.techs.domain.usecases

import com.techs.domain.common.Transformer
import com.techs.domain.entities.PinEntity
import com.techs.domain.repo.PinRepository
import io.reactivex.rxjava3.core.Observable

class GetPinDetails(
    transformer: Transformer<List<PinEntity>>,
    private val pinRepository: PinRepository
) : UseCase<List<PinEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<PinEntity>> {
        return pinRepository.getPins()
    }
}