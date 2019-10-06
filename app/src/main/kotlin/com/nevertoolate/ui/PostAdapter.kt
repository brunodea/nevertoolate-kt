package com.nevertoolate.ui

import android.content.Context
import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nevertoolate.R
import com.nevertoolate.api.model.Post
import com.nevertoolate.databinding.PostItemBinding

/**
 * Adapter for the list of the posts
 * @property context Context in which the application is running
 */
class PostAdapter(private val context: Context) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var posts: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: PostItemBinding = inflate(layoutInflater, R.layout.post_item, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder?.bind(posts[position])
    }


    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updatePosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PostViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }
}