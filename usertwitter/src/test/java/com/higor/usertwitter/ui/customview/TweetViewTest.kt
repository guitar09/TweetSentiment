package com.higor.usertwitter.ui.customview

import android.os.Build
import com.higor.core.test.BaseCustomViewTest
import com.higor.usertwitter.ui.customview.TweetViewTestRobot.arrange
import com.higor.usertwitter.ui.customview.TweetViewTestRobot.act
import com.higor.usertwitter.ui.customview.TweetViewTestRobot.assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class TweetViewTest : BaseCustomViewTest(){

    @Test
    fun `Whem custom tweetView init, should correct values`() {
        arrange {
            startCustomView("Um tweet aqui",  startActivityCustomView())
        }
        act {
            bindModel()
        }
        assert {
            checkComponentAsserts()
        }
    }

}