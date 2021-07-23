package com.higor.usertwitter.ui

import android.content.Context
import android.os.Looper
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.google.android.material.textfield.TextInputLayout
import com.google.common.truth.Truth.assertThat
import com.higor.core.test.robolectricBuilder
import com.higor.usertwitter.R
import com.higor.usertwitter.data.datasource.api.exception.NetworkConnectionException
import com.higor.usertwitter.domain.exception.TwitterUserException
import com.higor.usertwitter.domain.usecase.TweetUseCase
import com.higor.usertwitter.ui.dialog.SearchUserDialog
import com.higor.usertwitter.ui.stubs.Stubs
import com.higor.usertwitter.ui.viewmodel.UserTwitterViewModel
import io.mockk.*
import junit.framework.Assert.assertEquals
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowToast
import java.io.IOException

internal object UserTwitterActivityTestRobot {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var activity: UserTwitterActivity
    private val useCase = mockk<TweetUseCase>()

    fun getModule() : Module {

        val userTwitterViewModel : UserTwitterViewModel = spyk(UserTwitterViewModel(useCase))

        var mockModule = module {
            factory (override = true) { userTwitterViewModel }
        }

        return mockModule
    }

    class UserTwitterActivityTestRobotArrange {

        fun setUp() {

            activity = robolectricBuilder<UserTwitterActivity>(UserTwitterActivity.callThisActivity(context = context))
            executePendingTransactions()
        }

        fun mockApiSucess() {
            coEvery { useCase.execute(any()) } returns Stubs.getTweets()
        }

        fun mockApiErrorUserNotFound() {
            coEvery { useCase.execute(any()) } throws TwitterUserException()
        }

        fun mockApiError() {
            coEvery { useCase.execute(any()) } throws IOException()
        }

        fun mockApiInternetError() {
            coEvery { useCase.execute(any()) } throws NetworkConnectionException()
        }

    }

    class UserTwitterActivityTestRobotAct {

        fun searchText() {
            val userSearch = "higor"
            getSearchDialog().dialog?.findViewById<TextInputLayout>(R.id.inputSearchUserName)?.editText?.setText(userSearch)
            getSearchDialog().dialog?.findViewById<AppCompatButton>(R.id.btnSearch)?.performClick()
            executePendingTransactions()
            callShadowOf()
        }

        fun clickTweetItemInRecyclerView() {
            val recycler = activity.findViewById<RecyclerView>(R.id.rvTweet)
            recycler.measure(0, 0)
            recycler.getChildAt(0).performClick()
            executePendingTransactions()
        }

    }

    class UserTwitterActivityTestRobotAssert {

        fun isSearchUserOpen() {
            callShadowOf()
            assertThat(getSearchDialog()).isNotNull()
        }

        fun isShowTweets() {
            callShadowOf()
            val recycler = activity.findViewById<RecyclerView>(R.id.rvTweet)
            val actual = recycler.adapter?.itemCount
            val expected = 1

            assertEquals(expected, actual)
        }

        fun isShowMessageUserNotFound() {
            callShadowOf()
            val expected = "Usuário não encontrado"
            assertEquals(expected, ShadowToast.getTextOfLatestToast());
        }

        fun isShowMessageGenericError() {
            callShadowOf()
            val expected = "Aconteceu um problema, tente novamente"
            assertEquals(expected, ShadowToast.getTextOfLatestToast());
        }

        fun isShowMessageInternetError() {
            callShadowOf()
            val expected = "Verifique sua conexão com a internet, tente novamente"
            assertEquals(expected, ShadowToast.getTextOfLatestToast());
        }

        fun isOpenSentimentActivity() {
            callShadowOf()
            val intent = shadowOf(activity).peekNextStartedActivity()
            assertEquals("com.higor.sentimentanalyze.ui.SentimentAnalyzeActivity", intent.component?.className)
        }
    }

    fun executePendingTransactions(){
        activity.supportFragmentManager.executePendingTransactions()
    }

    fun callShadowOf() {
        shadowOf(Looper.getMainLooper()).idle()
    }

     fun getSearchDialog() : SearchUserDialog {
        return activity.supportFragmentManager.findFragmentByTag(activity.javaClass.name) as SearchUserDialog
    }

    fun arrange(func : UserTwitterActivityTestRobotArrange.() -> Unit) = UserTwitterActivityTestRobotArrange().apply(func)
    fun act(func : UserTwitterActivityTestRobotAct.() -> Unit) = UserTwitterActivityTestRobotAct().apply(func)
    fun assert(func : UserTwitterActivityTestRobotAssert.() -> Unit) = UserTwitterActivityTestRobotAssert().apply(func)
}