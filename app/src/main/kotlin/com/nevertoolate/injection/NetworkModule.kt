package com.nevertoolate.injection

import com.google.gson.Gson
import com.nevertoolate.api.RedditApiInterface
import com.nevertoolate.api.model.RedditListing
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
//    /**
//     * Provides the Post service implementation.
//     * @param retrofit the Retrofit object used to instantiate the service
//     * @return the Post service implementation.
//     */
//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideRedditApi(): Observable<RedditListing> {
//        return RedditApiInterface.create().getNewGetMotivatedPosts()
//    }
//
//    /**
//     * Provides the Retrofit object.
//     * @return the Retrofit object
//     */
//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideRedditInterface(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .build()
//    }



    // TODO try to use the Factory created in RedditApiInterface

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): RedditApiInterface {
        return retrofit.create(RedditApiInterface::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl(RedditApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}