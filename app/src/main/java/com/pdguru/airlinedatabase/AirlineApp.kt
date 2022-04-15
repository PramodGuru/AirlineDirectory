package com.pdguru.airlinedatabase

import android.app.Application
import com.pdguru.airlinedatabase.di.KoinGraph
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AirlineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        plantTimber()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AirlineApp)
            modules(KoinGraph.appModules())
        }
    }

    private fun plantTimber(){
        Timber.plant(LoggingTree(resources.getString(R.string.app_name)))
        Timber.d("Timber is planted")
    }

    class LoggingTree(private val applicationName: String) : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String {
            with(element) {
                return "$applicationName:$fileName:$lineNumber)#$methodName"
            }
        }
    }
}
