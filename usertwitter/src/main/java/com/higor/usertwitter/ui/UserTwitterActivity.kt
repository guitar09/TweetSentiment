package com.higor.usertwitter.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.higor.core.ErrorState
import com.higor.core.LoadingState
import com.higor.core.SucessState
import com.higor.sentimentanalyze.navigation.NavigationSentimentRoute
import com.higor.usertwitter.R
import com.higor.usertwitter.data.datasource.api.exception.NetworkConnectionException
import com.higor.usertwitter.domain.exception.TwitterUserException
import com.higor.usertwitter.ui.base.BaseActivity
import com.higor.usertwitter.ui.dialog.SearchUserDialog
import com.higor.usertwitter.ui.model.TweetUI
import com.higor.usertwitter.ui.viewmodel.UserTwitterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class UserTwitterActivity : BaseActivity(), TweetListAdapter.OnClickItemTweetAdapter {

    private val tweetViewModel: UserTwitterViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.rvTweet) }
    private val fabSearch: ExtendedFloatingActionButton by lazy { findViewById(R.id.fabSearch) }
    private val llyEmpty: LinearLayout by lazy { findViewById(R.id.llyEmpty) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.prbTweet) }
    private val adapter: TweetListAdapter by lazy { TweetListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_twitter)

        configObservers()
        configRecyclerView()
        configFloatButton()
        configInitOpen()

    }

    private fun configInitOpen() {
        showEmptyView(true)
        showSearchDialog()
    }

    private fun configFloatButton() {
        fabSearch.setOnClickListener {
            showSearchDialog()
        }
    }

    private fun showSearchDialog() {
        val searchDialog = SearchUserDialog()
        searchDialog.setOnSearchClickListener(object : SearchUserDialog.OnSearchClickListener {
            override fun OnClickSearch(text: String) {
                if (text.isNotEmpty()) tweetViewModel.fetchTweets(text)
            }

        })

        searchDialog.show(this.supportFragmentManager, this.javaClass.name)
    }

    private fun configRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun configView(tweets: List<TweetUI>) {
        adapter.submitList(tweets)
    }

    private fun configObservers() {

        tweetViewModel.getTweetsLiveDate.observe(this, Observer { status ->


            when (status) {
                is LoadingState -> {
                    configProgressView(true)
                }

                is SucessState -> {
                    configProgressView(false)
                    configView(status.data)
                }

                is ErrorState -> {
                    showEmptyView(true)
                    handleError(status.error)
                }
            }
        })
    }

    private fun handleError(error : Throwable) {
        when(error) {
            is TwitterUserException -> Toast.makeText(this, getString(R.string.user_not_found) , Toast.LENGTH_LONG).show()
            is NetworkConnectionException -> Toast.makeText(this, getString(R.string.internet_error) , Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgressView(visible: Boolean) {
        progressBar.isVisible = visible
    }

    private fun showEmptyView(visible: Boolean) {
        llyEmpty.isVisible = visible
        showProgressView(false)

    }

    private fun configProgressView(visible: Boolean) {
        showProgressView(visible)
        showEmptyView(false)
    }

    override fun onClickItem(tweet: TweetUI) {
        NavigationSentimentRoute().navigateToSentimentAnalyze(this, tweet.text)
    }

    companion object {

        fun callThisActivity(context: Context): Intent {
            return Intent(context, UserTwitterActivity::class.java).apply {
            }
        }
    }
}