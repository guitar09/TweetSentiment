package com.higor.tweetsentiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.higor.usertwitter.navigation.NagivationUserTwitterRoute

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            NagivationUserTwitterRoute().navigateToUserTwitter(this)
            finish()
        }, 2200)

    }
}