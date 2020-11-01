package io.indrian16.getrestapi.ui.user.view

import io.indrian16.getrestapi.model.User

interface UserView {

    fun showLoading()

    fun hideLoading()

    fun updatePosts(userList: List<User>)

    fun showError(throwable: Throwable)
}