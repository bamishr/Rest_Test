package io.indrian16.getrestapi.model

data class User(val id: Int,
                val name: String,
                val email: String)@Provides
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
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit =

            Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()