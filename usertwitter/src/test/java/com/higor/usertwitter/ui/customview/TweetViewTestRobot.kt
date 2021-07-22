package com.higor.usertwitter.ui.customview

import android.app.Activity
import android.widget.TextView
import com.higor.usertwitter.R
import junit.framework.Assert.assertEquals


internal object TweetViewTestRobot {

    private lateinit var tweetView: TweetView
    private lateinit var model: TweetView.Model

    private fun createModel(text : String) : TweetView.Model {
        return TweetView.Model(text)
    }


    class TweetViewTestRobotArrange {
        fun startCustomView(text : String, activity: Activity) {
            tweetView = TweetView(activity)
            model = createModel(text)
        }
    }

    class TweetViewTestRobotAct {
        fun bindModel() {
            tweetView.bind(model)
        }
    }

    class TweetViewTestRobotAssert {
        fun checkComponentAsserts() {

            val expectedText = "Um tweet aqui"
            val tweetTextView = tweetView.findViewById<TextView>(R.id.tvTitleTweetViewRow).text

            assertEquals(expectedText, tweetTextView)
        }
    }

    fun arrange(func : TweetViewTestRobotArrange.() -> Unit) = TweetViewTestRobotArrange().apply(func)
    fun act(func : TweetViewTestRobotAct.() -> Unit) = TweetViewTestRobotAct().apply(func)
    fun assert(func : TweetViewTestRobotAssert.() -> Unit) = TweetViewTestRobotAssert().apply(func)
}