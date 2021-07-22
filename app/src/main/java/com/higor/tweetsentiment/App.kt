package com.higor.tweetsentiment

import androidx.multidex.MultiDexApplication
import com.higor.usertwitter.UserTwitterConfig

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        UserTwitterConfig.start(this)
    }
}