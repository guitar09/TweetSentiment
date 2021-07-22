package com.higor.sentimentanalyze.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.higor.core.ErrorState
import com.higor.core.LoadingState
import com.higor.core.SucessState
import com.higor.sentimentanalyze.R
import com.higor.sentimentanalyze.data.datasource.api.exception.NetworkConnectionException
import com.higor.sentimentanalyze.ui.base.BaseActivity
import com.higor.sentimentanalyze.ui.customview.SentimentAnalyzeView
import com.higor.sentimentanalyze.ui.model.SentimentUI
import com.higor.sentimentanalyze.ui.viewmodel.SentimentAnaylizeViewModel
import com.higor.sentimentanalyze.domain.sentimentbo.SentimentStatus
import com.higor.sentimentanalyze.ui.utils.Emoji
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SentimentAnalyzeActivity : BaseActivity() {

    private val sentimentViewModel: SentimentAnaylizeViewModel by viewModel()
    private val progressBar: ProgressBar by lazy { findViewById(R.id.prbASentimentAnalyze) }
    val sentimentComponent: SentimentAnalyzeView by lazy { findViewById(R.id.sentimentComponent) }
    private val textAnalyze: String by lazy { intent.getStringExtra(ARGS_TEXT_SENTIMENT_ANALYZE) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentiment_analyze)

        setObservers()
        sentimentAnalyzeByText()
    }

    private fun sentimentAnalyzeByText() {
        sentimentViewModel.fetchAnalyzeText(textAnalyze)
    }

    private fun configView(model: SentimentUI) {

        try {

            val emoji = when (sentimentViewModel.getSentimentalUI(model.score)) {
                SentimentStatus.POSITIVE -> Emoji.POSITIVE
                SentimentStatus.NEUTRAL -> Emoji.NEUTRAL
                SentimentStatus.NEGATIVE -> Emoji.NEGATIVE
            }

            bindSentimentComponet(emoji, model.text)
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message ?: "", Toast.LENGTH_LONG).show()
        }

    }

    private fun bindComponent(emoji: String, text: String) {
        sentimentComponent.bind(SentimentAnalyzeView.Model(emoji, text))
        showAnalyzeComponent(true)
    }

    private fun bindSentimentComponet(emoji: String, text: String) {
        bindComponent(emoji, text)
    }

    private fun bindErrorComponent() {
        bindComponent(Emoji.ERROR, getString(R.string.ops))
    }

    private fun setObservers() {

        sentimentViewModel.getSentimentAnalyzeLiveDate.observe(this, Observer { status ->


            when (status) {
                is LoadingState -> {
                    showAnalyzeComponent(false)
                    showProgressView(true)
                }

                is SucessState -> {
                    showProgressView(false)
                    configView(status.data)

                }

                is ErrorState -> {
                    handleError(status.error)
                    bindErrorComponent()
                }
            }
        })

    }

    private fun handleError(error: Throwable) {
        when (error) {
            is NetworkConnectionException -> Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, getString(R.string.message_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgressView(visible: Boolean) {
        progressBar.isVisible = visible
    }

    private fun showAnalyzeComponent(visible: Boolean) {
        sentimentComponent.isVisible = visible
    }


    companion object {

        private const val ARGS_TEXT_SENTIMENT_ANALYZE = "TEXT_SENTIMENT_ANALYZE"

        fun callThisActivity(context: Context, text: String): Intent {
            return Intent(context, SentimentAnalyzeActivity::class.java).apply {
                putExtra(ARGS_TEXT_SENTIMENT_ANALYZE, text)
            }
        }
    }
}