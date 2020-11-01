package io.indrian16.getrestapi.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.indrian16.getrestapi.network.ApiService
import io.indrian16.getrestapi.util.AppConstant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit =

            Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService =

            retrofit.create(ApiService::class.java)
}