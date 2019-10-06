package com.nevertoolate.architecture

import android.support.annotation.StringRes
import com.nevertoolate.api.model.RedditListing

/**
 * Interface providing required method for a view displaying Get Motivated cards
 */
interface PostView : BaseView {
    /**
     * Updates the previous cards by the specified ones
     * @param cards the list of cards that will replace existing ones
     */
    fun updatePosts(cards: RedditListing)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}