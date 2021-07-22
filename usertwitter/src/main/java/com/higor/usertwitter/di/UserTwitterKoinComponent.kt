package com.higor.usertwitter.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

internal interface UserTwitterKoinComponent : KoinComponent {
    override fun getKoin(): Koin {
        return UserTwitterKoinContext.koinApp.koin
    }
}