package com.higor.sentimentanalyze.ui.customview

import android.app.Activity
import android.widget.TextView
import com.higor.sentimentanalyze.R
import junit.framework.Assert.assertEquals

internal object SentimentAnalyzeViewTestRobot {

    private lateinit var sentimentView : SentimentAnalyzeView
    private lateinit var model : SentimentAnalyzeView.Model

    private fun createModel(emoji : String, text : String) : SentimentAnalyzeView.Model {
        return SentimentAnalyzeView.Model(emoji, text)
    }

    class SentimentAnalyzeViewTestRobotArrange {

        fun startCustomView(emoji : String, text : String, activity: Activity) {
            sentimentView = SentimentAnalyzeView(activity)
            model = createModel(emoji, text)
        }
    }

    class SentimentAnalyzeViewTestRobotAct {
        fun bindModel() {
            sentimentView.bind(model)
        }
    }

    class SentimentAnalyzeViewTestRobotAssert {
        fun checkComponentAsserts() {

            val expectedEmoji = "Um emoji aqui"
            val expectedText = "Um titulo"
            val emojiTextView = sentimentView.findViewById<TextView>(R.id.tvSentimentEmoji).text
            val titleTextView = sentimentView.findViewById<TextView>(R.id.tvSentimenTitle).text

            assertEquals(expectedEmoji, emojiTextView)
            assertEquals(expectedText, titleTextView)
        }
    }


    fun arrange(func : SentimentAnalyzeViewTestRobotArrange.() -> Unit) = SentimentAnalyzeViewTestRobotArrange().apply(func)
    fun act(func : SentimentAnalyzeViewTestRobotAct.() -> Unit) = SentimentAnalyzeViewTestRobotAct().apply(func)
    fun assert(func : SentimentAnalyzeViewTestRobotAssert.() -> Unit) = SentimentAnalyzeViewTestRobotAssert().apply(func)
}