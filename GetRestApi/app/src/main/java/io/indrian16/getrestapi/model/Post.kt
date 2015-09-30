package io.indrian16.getrestapi.model

data class Post(val userId: Int,
                val id: Int,
                val title: String,
                val body: String)
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
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit =

            Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()