package com.higor.sentimentanalyze.ui

import android.os.Build
import com.higor.sentimentanalyze.di.SentimentKoinContext
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.higor.sentimentanalyze.ui.SentimentAnalyzeActivityTestRobot.arrange
import com.higor.sentimentanalyze.ui.SentimentAnalyzeActivityTestRobot.act
import com.higor.sentimentanalyze.ui.SentimentAnalyzeActivityTestRobot.assert
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTestRule

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class SentimentAnalyzeActivityTest {

    @After
    fun tearDown() {
        SentimentAnalyzeActivityTestRobot.tearDown()
    }

    @get:Rule
    val koinRule = KoinTestRule.create {
        this.modules(SentimentAnalyzeActivityTestRobot.getModule())
        SentimentKoinContext.koinApp = this
    }

    @Test
    fun `When text sentiment is positive, should show emoji positive`() {

        arrange {
            mockPositive()
            setUp()
        }

        act {

        }

        assert {
            isShowPositiveEmoji()
        }
    }

    @Test
    fun `When text sentiment is neutral, should show emoji neutral`() {

        arrange {
            mockNeutral()
            setUp()
        }

        act {

        }

        assert {
          isShowNeutralEmoji()
        }
    }

    @Test
    fun `When text sentiment is negative, should show emoji negative`() {

        arrange {
            mockNegative()
            setUp()
        }

        act {

        }

        assert {
            isShowNegativeEmoji()
        }
    }

    @Test
    fun `When service sentiment error, should show message and emoji error`() {

        arrange {
            mockAPIError()
            setUp()
        }

        act {

        }

        assert {
            isShowError()
        }
    }

    @Test
    fun `When phone no internet, should show message and emoji error`() {

        arrange {
            mockInternetError()
            setUp()
        }

        act {

        }

        assert {
            isShowError()
        }
    }
}
