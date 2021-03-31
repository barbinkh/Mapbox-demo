package com.techs.domain.repo

import com.techs.domain.entities.PinEntity
import io.reactivex.rxjava3.core.Observable

interface PinRepository {
    fun getPins(): Observable<List<PinEntity>>
}