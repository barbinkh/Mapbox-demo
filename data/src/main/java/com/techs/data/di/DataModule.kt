package com.techs.data.di

import com.techs.data.repo.PinRepositoryImpl
import com.techs.domain.repo.PinRepository
import org.koin.dsl.module

val dataModule = module {
    factory<PinRepository> { PinRepositoryImpl(get()) }
}