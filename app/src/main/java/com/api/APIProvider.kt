package com.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIProvider {

    @POST("login")
    @FormUrlEncoded
    fun loginPost(@Field("username") username: String,
                         @Field("password") password: String): Call<loginGood>
}


//**App Utils**

object ApiUtils {

    val BASE_URL = "http://10.0.2.2:8080/"

    val apiService: APIProvider
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIProvider::class.java)

}


data class loginGood(val error: Boolean, val message: String, val token: Any) {
}