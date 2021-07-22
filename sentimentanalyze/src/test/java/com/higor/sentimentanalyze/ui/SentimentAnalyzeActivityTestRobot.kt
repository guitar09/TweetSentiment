package com.higor.sentimentanalyze.ui

import android.content.Context
import android.os.Looper
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.higor.core.test.robolectricBuilder
import com.higor.sentimentanalyze.R
import com.higor.sentimentanalyze.data.datasource.api.exception.GenericNetworkException
import com.higor.sentimentanalyze.data.datasource.api.exception.NetworkConnectionException
import com.higor.sentimentanalyze.ui.stubs.Stubs
import com.higor.sentimentanalyze.ui.viewmodel.SentimentAnaylizeViewModel
import com.higor.sentimentanalyze.domain.usecase.SentimentUseCase
import com.higor.sentimentanalyze.domain.usecase.SentimentalAnalyzeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.Shadows
import java.io.IOException

internal object SentimentAnalyzeActivityTestRobot {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var activity: SentimentAnalyzeActivity
    private val useCase = mockk<SentimentUseCase>()
    private val useCaseAnalyse = SentimentalAnalyzeUseCase()

    fun tearDown() {
        activity.finish()
        unmockkAll()
    }

    fun getModule() : Module {

        val sentimentViewModel : SentimentAnaylizeViewModel = spyk(SentimentAnaylizeViewModel(useCase, useCaseAnalyse))

        var mockModule = module {
            factory (override = true) { sentimentViewModel }
        }

        return mockModule
    }

    class SentimentAnalyzeActivityTestRobotArrange {

        fun setUp() {

            activity = robolectricBuilder(SentimentAnalyzeActivity.callThisActivity(context = context, ""))
            executePendingTransactions()
        }

        fun mockPositive() {
            coEvery { useCase.execute("") } returns Stubs.getSentimentPositive()
        }

        fun mockNeutral() {
            coEvery { useCase.execute("") } returns Stubs.getSentimentNeutral()
        }

        fun mockNegative() {
            coEvery { useCase.execute("") } returns Stubs.getSentimentNegative()
        }

        fun mockAPIError() {
            coEvery { useCase.execute("") } throws GenericNetworkException()
        }

        fun mockInternetError() {
            coEvery { useCase.execute("") } throws NetworkConnectionException()
        }

    }

    class SentimentAnalyzeActivityTestRobotAct {

    }

    class SentimentAnalyzeActivityTestRobotAssert {

        fun isShowPositiveEmoji() {
            callShadowOf()
            val positiveExpected = "\uD83D\uDE00"
            val actual = activity.sentimentComponent.findViewById<TextView>(R.id.tvSentimentEmoji).text

            assertEquals(positiveExpected, actual)
        }

        fun isShowNeutralEmoji() {
            callShadowOf()
            val neutralExpected = "\uD83D\uDE10"
            val actual = activity.sentimentComponent.findViewById<TextView>(R.id.tvSentimentEmoji).text

            assertEquals(neutralExpected, actual)
        }

        fun isShowNegativeEmoji() {
            callShadowOf()
            val negativeExpected = "\uD83D\uDE14"
            val actual = activity.sentimentComponent.findViewById<TextView>(R.id.tvSentimentEmoji).text

            assertEquals(negativeExpected, actual)
        }

        fun isShowError() {
            callShadowOf()
            val errorEmojiExpected = "\uD83D\uDEF8"
            val actualEmoji = activity.sentimentComponent.findViewById<TextView>(R.id.tvSentimentEmoji).text

            val errorMessageExpected = "Oooppppssss\nAlgo deu errado, tente novamente :)"
            val actualMessage = activity.sentimentComponent.findViewById<TextView>(R.id.tvSentimenTitle).text

            assertEquals(errorEmojiExpected, actualEmoji)
            assertEquals(errorMessageExpected, actualMessage)
        }
    }

    fun executePendingTransactions(){
        activity.supportFragmentManager.executePendingTransactions()
    }

    fun callShadowOf() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
    }


    fun arrange(func : SentimentAnalyzeActivityTestRobotArrange.() -> Unit) = SentimentAnalyzeActivityTestRobotArrange().apply(func)
    fun act(func : SentimentAnalyzeActivityTestRobotAct.() -> Unit) = SentimentAnalyzeActivityTestRobotAct().apply(func)
    fun assert(func : SentimentAnalyzeActivityTestRobotAssert.() -> Unit) = SentimentAnalyzeActivityTestRobotAssert().apply(func)
}