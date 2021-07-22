package com.higor.usertwitter.ui.dialog

import android.os.Build
import com.higor.usertwitter.ui.dialog.SearchUserDialogTestRobot.act
import com.higor.usertwitter.ui.dialog.SearchUserDialogTestRobot.arrange
import com.higor.usertwitter.ui.dialog.SearchUserDialogTestRobot.assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class SearchUserDialogTest {


    @Test
    fun `Check content SearchDialog`() {
        arrange {
            setUp()
        }

        act {
            showDialog()
        }

        assert {
            checkContentDialog()
        }
    }

    @Test
    fun `Check button click SearchDialog`() {
        arrange {
            setUp()
        }

        act {
            showDialog()
        }

        assert {
            checkClickButtomDialog()
        }
    }
}