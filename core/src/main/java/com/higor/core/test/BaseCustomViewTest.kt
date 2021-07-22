package com.higor.core.test

import android.app.Activity
import org.robolectric.Robolectric

open class BaseCustomViewTest {
    fun startActivityCustomView() = Robolectric.buildActivity(Activity::class.java).create().get()
}