package com.higor.usertwitter.ui.dialog

import android.app.Dialog
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputLayout
import com.higor.core.test.robolectricBuilder
import com.higor.usertwitter.R
import junit.framework.Assert.assertEquals
import org.robolectric.Shadows


internal object SearchUserDialogTestRobot {

    private lateinit var dialog: SearchUserDialog
    private lateinit var supportFragmentManager: FragmentManager

    private fun getDialog() : Dialog?{
        return dialog.dialog
    }

    class SearchUserDialogTestRobotArrange {

        fun setUp() {
            val activity = robolectricBuilder<FragmentActivity>()
            supportFragmentManager = activity.supportFragmentManager
        }

    }

    class SearchUserDialogTestRobotAct {

        fun showDialog() {
            dialog = SearchUserDialog()
            dialog.show(supportFragmentManager, "")
            callShadowOf()
        }
    }

    class SearchUserDialogTestRobotAssert {

        fun checkContentDialog() {
            assertEquals(true, dialog.isVisible)
            val titleDialog = getDialog()?.findViewById<TextView>(R.id.tvTitleSearch)
            val titleExpected = "Digite o nome do usuário no Twitter"

            with(titleDialog) {
                assertEquals(true, this?.isVisible)
                assertEquals(titleExpected, this?.text)
            }

            val edtTextUserDialog = getDialog()?.findViewById<TextInputLayout>(R.id.inputSearchUserName)
            val edtTextHintExpected = "Usuário"

            with(edtTextUserDialog) {
                assertEquals(true, this?.isVisible)
                assertEquals(edtTextHintExpected, this?.editText?.hint)
            }

            val btnSearchUserDialog = getDialog()?.findViewById<AppCompatButton>(R.id.btnSearch)
            val btnTitleExpected = "Pesquisar"

            with(btnSearchUserDialog) {
                assertEquals(true, this?.isVisible)
                assertEquals(btnTitleExpected, this?.text)
            }
        }

        fun checkClickButtomDialog() {

            val btnSearchUserDialog = getDialog()?.findViewById<AppCompatButton>(R.id.btnSearch)
            var clickExpected = 0

            btnSearchUserDialog?.setOnClickListener {
                clickExpected++
            }
            btnSearchUserDialog?.performClick()

            assertEquals(1, clickExpected)
        }
    }

    fun callShadowOf() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
    }


    fun arrange(func : SearchUserDialogTestRobotArrange.() -> Unit) = SearchUserDialogTestRobotArrange().apply(func)
    fun act(func : SearchUserDialogTestRobotAct.() -> Unit) = SearchUserDialogTestRobotAct().apply(func)
    fun assert(func : SearchUserDialogTestRobotAssert.() -> Unit) = SearchUserDialogTestRobotAssert().apply(func)
}


