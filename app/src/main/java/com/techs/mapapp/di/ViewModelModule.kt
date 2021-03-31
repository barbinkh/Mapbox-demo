package com.techs.mapapp.di

import com.techs.mapapp.map.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module() {
    viewModel { MapViewModel(get(), get(), get()) }
}