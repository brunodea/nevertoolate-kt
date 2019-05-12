package com.nevertoolate

import com.nevertoolate.api.RedditApiInterface
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class RedditApiInterfaceTest {

    @Test
    fun testGetNewGetMotivatedResponse() {
        // TODO Mock Reddit API response
        RedditApiInterface.create().getNewGetMotivatedPosts()
            .observeOn(Schedulers.trampoline())
            .subscribeOn(Schedulers.trampoline())
            .subscribe(
                { postList -> print(postList) },
                { print(it.message) }
            )
    }
}
