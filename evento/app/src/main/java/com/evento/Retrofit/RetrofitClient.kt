package com.evento.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var ourInstance:Retrofit?= null
    val instance:Retrofit
        get() {
            if (ourInstance == null)
                ourInstance = Retrofit.Builder()
                    .baseUrl("http://localhost:3000/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return ourInstance!!
        }
}