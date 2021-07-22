package com.higor.usertwitter.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.higor.usertwitter.ui.viewmodel.UserTwitterViewModelTestRobot.act
import com.higor.usertwitter.ui.viewmodel.UserTwitterViewModelTestRobot.arrange
import com.higor.usertwitter.ui.viewmodel.UserTwitterViewModelTestRobot.assert
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

class UserTwitterViewModelTest {

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