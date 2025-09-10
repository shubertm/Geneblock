package com.infbyte.geneblock

import android.app.Application
import com.infbyte.geneblock.di.repoModule
import com.infbyte.geneblock.di.viewModelModule
import com.infbyte.shared.di.networkModule
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule, repoModule, viewModelModule)
        }
    }
}