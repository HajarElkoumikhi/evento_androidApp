package com.evento.Retrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import io.reactivex.Observable

interface INodeJS {
    @POST("login")
    @FormUrlEncoded
    fun loginUser(@Field("email") email:String,
                  @Field("password") password:String):Observable<String>
}