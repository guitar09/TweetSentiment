package com.higor.usertwitter.navigation

import android.content.Context
import com.higor.usertwitter.ui.UserTwitterActivity

class NagivationUserTwitterRoute : NagivationUserTwitter {
    override fun navigateToUserTwitter(context: Context) {
        context.startActivity(UserTwitterActivity.callThisActivity(context))
    }
}