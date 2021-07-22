package com.higor.sentimentanalyze.ui.customview

import android.os.Build
import com.higor.core.test.BaseCustomViewTest
import com.higor.sentimentanalyze.ui.customview.SentimentAnalyzeViewTestRobot.act
import com.higor.sentimentanalyze.ui.customview.SentimentAnalyzeViewTestRobot.arrange
import com.higor.sentimentanalyze.ui.customview.SentimentAnalyzeViewTestRobot.assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class SentimentAnalyzeViewTest : BaseCustomViewTest() {

    @Test
    fun `Whem custom view init, should correct values`() {
        arrange {
            startCustomView("Um emoji aqui", "Um titulo", startActivityCustomView())
        }
        act {
            bindModel()
        }
        assert {
            checkComponentAsserts()
        }
    }

}