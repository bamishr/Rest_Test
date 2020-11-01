package io.indrian16.getrestapi.ui.todo

import dagger.Module
import dagger.Provides
import io.indrian16.getrestapi.network.ApiService
import io.indrian16.getrestapi.ui.todo.presenter.TodoPresenter
import io.indrian16.getrestapi.ui.todo.presenter.TodoPresenterImpl
import io.indrian16.getrestapi.ui.todo.view.TodoFragment
import io.indrian16.getrestapi.ui.todo.view.TodoView

@Module
class TodoFragmentModule {

    @Provides
    internal fun provideTodoView(todoFragment: TodoFragment): TodoView = todoFragment

    @Provides
    internal fun provideTodoPresenter(todoView: TodoView, apiService: ApiService): TodoPresenter =

            TodoPresenterImpl(todoView, apiService)
}