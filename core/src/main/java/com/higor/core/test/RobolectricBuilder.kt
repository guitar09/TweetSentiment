package com.higor.core.test

import android.app.Activity
import android.content.Intent
import org.robolectric.Robolectric

inline fun <reified T : Activity> robolectricBuilder(intent: Intent = Intent()) =
    Robolectric.buildActivity(T::class.java, intent).create().resume().get()