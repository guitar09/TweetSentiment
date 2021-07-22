package com.higor.usertwitter.di

import android.content.Context
import com.higor.usertwitter.userTwitterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

internal object UserTwitterKoinContext {

    lateinit var koinApp: KoinApplication

    fun start(appContext: Context) {
        koinApp = koinApplication {
            androidContext(appContext)
            modules(userTwitterModule)
        }
    }
}