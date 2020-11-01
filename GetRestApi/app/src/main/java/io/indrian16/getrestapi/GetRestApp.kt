package io.indrian16.getrestapi

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.indrian16.getrestapi.di.component.DaggerAppComponent
import io.indrian16.getrestapi.di.module.AppModule
import javax.inject.Inject

class GetRestApp : Application(), HasActivityInjector {

    @Inject internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .appModule(AppModule())
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {

        return dispatchingAndroidInjector
    }
}