package io.indrian16.getrestapi.ui.post.view

import io.indrian16.getrestapi.model.Post

interface PostView {

    fun updatePost(postList: List<Post>)

    fun showLoading()

    fun hideLoading()

    fun showError(error: Throwable)
}