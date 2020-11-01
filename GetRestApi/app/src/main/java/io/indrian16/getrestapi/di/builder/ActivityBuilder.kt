package io.indrian16.getrestapi.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.getrestapi.ui.main.MainActivityModule
import io.indrian16.getrestapi.ui.main.MainActivityProvide
import io.indrian16.getrestapi.ui.main.view.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityProvide::class])
    abstract fun bindMainActivity(): MainActivity
}