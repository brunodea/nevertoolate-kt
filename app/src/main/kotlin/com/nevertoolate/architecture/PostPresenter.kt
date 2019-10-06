package com.nevertoolate.architecture

import com.nevertoolate.api.RedditApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {

    @Inject
    lateinit var redditApi: RedditApiInterface

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadRedditPosts()
    }

    override fun onViewDestroyed() {
        subscription?.dispose()

    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or shows error if
     * any.
     */
    fun loadRedditPosts() {
        view.showLoading()
        subscription = redditApi
            .getNewGetMotivatedPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                view.hideLoading() }
            .subscribe(
                {
                        posts -> view.updatePosts(posts)
                },
                {
                    view.showError("TODO error")
                }
            )


        val retrofit = retrofit2.Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RedditApiInterface.BASE_URL)
            .build()

    }

}
