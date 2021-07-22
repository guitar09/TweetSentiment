package com.higor.sentimentanalyze.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.higor.sentimentanalyze.ui.viewmodel.SentimentAnaylizeViewModelTestRobot.act
import com.higor.sentimentanalyze.ui.viewmodel.SentimentAnaylizeViewModelTestRobot.arrange
import com.higor.sentimentanalyze.ui.viewmodel.SentimentAnaylizeViewModelTestRobot.assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SentimentAnaylizeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When api return success, should return state is success`() {
        arrange {
            mockSuccess()
        }

        act {
            fetchViewModel()
        }

        assert {
            checkSuccessViewModel()
        }
    }

    @Test
    fun `When api return error, should return state is error`() {
        arrange {
            mockError()
        }

        act {
            fetchViewModel()
        }

        assert {
            checkErrorViewModel()
        }
    }
}