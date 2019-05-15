package com.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


interface APIProvider {

    @POST("login")
    @FormUrlEncoded
    fun loginPost(@Field("username") username: String,
                         @Field("password") password: String): Call<loginGood>

    @GET("allevents")
    fun getEvents(): Call<allEvents>
}


//**App Utils**

object ApiUtils {

    val BASE_URL = "http://10.0.2.2:8080/"

    val apiService: APIProvider
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIProvider::class.java)

}


data class loginGood(val error: Boolean, val message: String, val token: Any) {
}


data class theEvents(val _id: String, val title: String, val description: String, val addressLine: String, val city: String,
                  val country: String, val facebookProfile: String, val instagramProfile: String, val website: String,
                  val hotline: String, val validAge: String, val maxUsers: String, val usersCounter: String,
                  val date: String, val ticketPrice: String, val __v: String){

}


data class allEvents(val error: Boolean = false, val message: String = "dede", val data: Array<Any>, val tata: Array<Int>){}