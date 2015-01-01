package io.indrian16.getrestapi.ui.todo.view

import io.indrian16.getrestapi.model.Todo

interface TodoView {

    fun showLoading()

    fun hideLoading()

    fun updatePosts(todoList: List<Todo>)

    fun showError(throwable: Throwable)
}