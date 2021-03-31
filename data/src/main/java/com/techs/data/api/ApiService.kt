package com.techs.data.api

import com.techs.data.entities.PinData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("features.json")
    fun getPins(@Query("app_mode") appMode: String): Observable<List<PinData>>
}