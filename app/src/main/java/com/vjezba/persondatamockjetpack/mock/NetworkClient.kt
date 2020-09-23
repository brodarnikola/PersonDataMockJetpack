package com.vjezba.persondatamockjetpack.mock

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {

    @GET("login1")
    fun login() : Call<List<LoginResponse>>

}


object NetworkClient {

    fun create() : ApiService {

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.mocky.io/v2/")
            .build().create(ApiService::class.java)

    }

}