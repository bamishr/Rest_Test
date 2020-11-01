package io.indrian16.getrestapi.ui.post

import dagger.Module
import dagger.Provides
import io.indrian16.getrestapi.network.ApiService
import io.indrian16.getrestapi.ui.post.presenter.PostPresenter
import io.indrian16.getrestapi.ui.post.presenter.PostPresenterImpl
import io.indrian16.getrestapi.ui.post.view.PostFragment
import io.indrian16.getrestapi.ui.post.view.PostView

@Module
class PostFragmentModule {

    @Provides
    internal fun providePostView(postFragment: PostFragment): PostView = postFragment

    @Provides
    internal fun providePostPresenter(postView: PostView, apiService: ApiService): PostPresenter =

            PostPresenterImpl(postView, apiService)
}