package io.indrian16.getrestapi.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.getrestapi.ui.post.PostFragmentModule
import io.indrian16.getrestapi.ui.post.view.PostFragment
import io.indrian16.getrestapi.ui.todo.TodoFragmentModule
import io.indrian16.getrestapi.ui.todo.view.TodoFragment
import io.indrian16.getrestapi.ui.user.UserFragmentModule
import io.indrian16.getrestapi.ui.user.view.UserFragment

@Module
abstract class MainActivityProvide {

    @ContributesAndroidInjector(modules = [PostFragmentModule::class])
    abstract fun providePostFragment(): PostFragment

    @ContributesAndroidInjector(modules = [TodoFragmentModule::class])
    abstract fun provideTodoFragment(): TodoFragment

    @ContributesAndroidInjector(modules = [UserFragmentModule::class])
    abstract fun provideUserFragment(): UserFragment
}