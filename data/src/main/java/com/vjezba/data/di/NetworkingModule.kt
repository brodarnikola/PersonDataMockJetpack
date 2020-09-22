package com.vjezba.data.di

import org.koin.dsl.module

private const val BASE_URL = "https://api.github.com/"


val networkingModule = module {

    //it creates a singleton that can be used across the app as a singular instance.
    // it has all the time the same value, and it point to the same reference in memory
    /*single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }
    single {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>().create(GithubRepositoryApi::class.java) }*/
}
