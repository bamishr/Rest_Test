package io.indrian16.getrestapi.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.indrian16.getrestapi.GetRestApp
import io.indrian16.getrestapi.di.builder.ActivityBuilder
import io.indrian16.getrestapi.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    fun inject(getRestApp: GetRestApp)
}