package io.indrian16.getrestapi.ui.user

import dagger.Module
import dagger.Provides
import io.indrian16.getrestapi.network.ApiService
import io.indrian16.getrestapi.ui.user.presenter.UserPresenter
import io.indrian16.getrestapi.ui.user.presenter.UserPresenterImpl
import io.indrian16.getrestapi.ui.user.view.UserFragment
import io.indrian16.getrestapi.ui.user.view.UserView

@Module
class UserFragmentModule {

    @Provides
    internal fun provideUserView(userFragment: UserFragment): UserView = userFragment

    @Provides
    internal fun provideUserPresenter(userView: UserView, apiService: ApiService): UserPresenter =

            UserPresenterImpl(userView, apiService)
}