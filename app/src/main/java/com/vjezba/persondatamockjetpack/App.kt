package com.vjezba.persondatamockjetpack

import android.app.Activity
import android.app.Application
import com.vjezba.data.di.databaseModule
import com.vjezba.data.di.networkingModule
import com.vjezba.data.di.repositoryModule
import com.vjezba.persondatamockjetpack.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {


    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val appModules = listOf(presentationModule)
        //val interactionModules = listOf(interactionModule)
        val dataModules = listOf(networkingModule, repositoryModule, databaseModule)

        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
              modules(appModules + dataModules)
            //modules(appModules + interactionModules + dataModules)
        }
    }

}

