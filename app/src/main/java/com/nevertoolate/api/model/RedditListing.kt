package com.nevertoolate.api.model

data class RedditListing(
    val data: RedditData
)

data class RedditData(
    val children: List<RedditDataEntry>
)

data class RedditDataEntry(
    val data: Post
)

data class Post(
    val title: String,
    val url: String,
    val id: String
)