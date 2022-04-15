package com.pdguru.airlinedatabase.di

import android.content.Context
import com.pdguru.airlinedatabase.BASE_URL
import com.pdguru.airlinedatabase.MainViewModel
import com.pdguru.airlinedatabase.details.AirlineDetailViewModel
import com.pdguru.airlinedatabase.networking.HttpClientProvider
import com.pdguru.airlinedatabase.networking.MoshiFactory
import com.pdguru.airlinedatabase.networking.RetrofitFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinGraph {

    private val clientProvider = HttpClientProvider()

    fun appModules(): List<Module> {
        return listOf(networkModule, baseModule)
    }

    private val baseModule = module {
        single { androidApplication().resources }
        single { androidContext().getSharedPreferences("AIRLINE_DB", Context.MODE_PRIVATE) }

        viewModel { MainViewModel(get(), clientProvider.getOkHttpClient()) }
        viewModel { AirlineDetailViewModel(get()) }
    }

    private val networkModule = module {
        single { MoshiFactory.createMoshi() }
        single { RetrofitFactory.createRetrofit(get(), clientProvider.getOkHttpClient(), BASE_URL) }
    }
}
