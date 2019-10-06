package com.nevertoolate

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.nevertoolate.api.model.Post
import com.nevertoolate.api.model.RedditListing
import com.nevertoolate.architecture.BaseActivity
import com.nevertoolate.architecture.PostPresenter
import com.nevertoolate.architecture.PostView
import com.nevertoolate.databinding.ActivityMainBinding
import com.nevertoolate.ui.PostAdapter

// https://proandroiddev.com/mvp-architecture-with-kotlin-dagger-2-retrofit-rxandroid-and-databinding-17bffe27393d

/**
 * Activity displaying the list of posts
 */
class MainActivity : BaseActivity<PostPresenter>(), PostView {


    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * The adapter for the list of posts
     */
    private val postsAdapter = PostAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updatePosts(cards: RedditListing) {
        val iterator = cards.data.children.iterator()
        var posts = mutableListOf<Post>()
        iterator.forEach {
            posts.add(it.data)
        }
        postsAdapter.updatePosts(posts)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }
}