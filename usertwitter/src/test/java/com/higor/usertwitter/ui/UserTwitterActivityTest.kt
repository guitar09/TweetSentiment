package com.higor.usertwitter.ui

import android.os.Build

import com.higor.usertwitter.di.UserTwitterKoinContext
import com.higor.usertwitter.ui.UserTwitterActivityTestRobot.act
import com.higor.usertwitter.ui.UserTwitterActivityTestRobot.arrange
import com.higor.usertwitter.ui.UserTwitterActivityTestRobot.assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTestRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class UserTwitterActivityTest {

    @get:Rule
    val koinRule = KoinTestRule.create {
        this.modules(UserTwitterActivityTestRobot.getModule())
        UserTwitterKoinContext.koinApp = this
    }

    @Test
    fun `When open activity, should search user is open`() {
        arrange {
            setUp()
        }

        act {

        }

        assert {
          isSearchUserOpen()
        }
    }

    @Test
    fun `When search user in dialog, should show tweets in recyclerView`() {
        arrange {
            mockApiSucess()
            setUp()
        }

        act {
            searchText()
        }

        assert {
            isShowTweets()
        }
    }

    @Test
    fun `When search not exists user in dialog, should show custom error`() {
        arrange {
            mockApiErrorUserNotFound()
            setUp()
        }

        act {
            searchText()
        }

        assert {
            isShowMessageUserNotFound()
        }
    }

    @Test
    fun `When api twitter error, should show generic error`() {
        arrange {
            mockApiError()
            setUp()
        }

        act {
            searchText()
        }

        assert {
            isShowMessageGenericError()
        }
    }

    @Test
    fun `When phone no internet, should show internet error`() {
        arrange {
            mockApiInternetError()
            setUp()
        }

        act {
            searchText()
        }

        assert {
            isShowMessageInternetError()
        }
    }

    @Test
    fun `When click in tweet, should show sentiment analyze activity`() {
        arrange {
            mockApiSucess()
            setUp()
        }

        act {
            searchText()
            clickTweetItemInRecyclerView()
        }

        assert {
            isOpenSentimentActivity()
        }
    }
}