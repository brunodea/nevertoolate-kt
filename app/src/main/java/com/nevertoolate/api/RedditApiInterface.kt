package com.nevertoolate.api

import com.nevertoolate.api.model.RedditListing
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RedditApiInterface {

    @GET("r/getmotivated/new/.json")
    fun getNewGetMotivatedPosts(): Observable<RedditListing>

    companion object Factory {

        private const val BASE_URL = "https://www.reddit.com/"

        fun create(): RedditApiInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RedditApiInterface::class.java)
        }
    }
}