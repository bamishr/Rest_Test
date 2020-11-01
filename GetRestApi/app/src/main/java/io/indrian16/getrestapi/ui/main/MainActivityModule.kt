package io.indrian16.getrestapi.ui.main

import dagger.Module
import dagger.Provides
import io.indrian16.getrestapi.ui.main.presenter.MainPresenter
import io.indrian16.getrestapi.ui.main.presenter.MainPresenterImpl
import io.indrian16.getrestapi.ui.main.view.MainActivity
import io.indrian16.getrestapi.ui.main.view.MainView

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainView(mainActivity: MainActivity): MainView = mainActivity

    @Provides
    internal fun provideMainPresenter(mainView: MainView): MainPresenter =

            MainPresenterImpl(mainView)
}