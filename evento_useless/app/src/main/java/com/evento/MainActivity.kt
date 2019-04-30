package com.evento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.evento.Retrofit.INodeJS
import com.evento.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    lateinit var myAPI:INodeJS
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        System.out.println("esddfsd")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init API
        val retrofit = RetrofitClient.instance


        myAPI = retrofit.create(INodeJS::class.java)

        login_button.setOnClickListener {
//            login(edt_email.text.toString(), edt_password.text.toString())
            Toast.makeText(this, edt_email.text.toString(), Toast.LENGTH_LONG).show()
            sendPostRequest(edt_email.text.toString(), edt_password.text.toString())
//            URL("https://google.com").readText()
        }
    }

    fun sendPostRequest(userName:String, password:String) {
        println(userName)
//        var reqParam = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8")
//        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
//        val mURL = URL("127.0.0.1:8080")

    }

        private fun login(email: String, password: String) {

        Toast.makeText(this@MainActivity, "Login success", Toast.LENGTH_SHORT).show()

        compositeDisposable.add(myAPI.loginUser(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { message ->
                if (message.contains("encrypted_password"))
                    Toast.makeText(this@MainActivity, "Login success", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        )
    }


    fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "U'll connect soon bro' !", Toast.LENGTH_SHORT)
        myToast.show()
    }

}


//class MainActivity : AppCompatActivity() {
//    val BASE_URL = "https://api.github.com/search/"
//    var tv_user: TextView? = null
//    var str:String = ""
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
////        tv_user = findViewById(R.id.tv_users)
////        getUsers()
//    }
//    // function to call server and update ui
//    fun getUsers() {
//        var retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var api = retrofit.create(Api::class.java)
//
//        var call = api.users
//
//        call.enqueue(object : Callback<UsersList> {
//
//            override fun onResponse(call: Call<UsersList>?, response: Response<UsersList>?) {
//                var usres = response?.body()
//                var user = usres?.users
//                var length = user!!.size
//
//                for (i in 0 until length) {
//                    str = str + "\n" + user.get(i).id + " " + user.get(i).login
//                }
//
//                tv_user!!.text = str
//            }
//
//            override fun onFailure(call: Call<UsersList>?, t: Throwable?) {
//                Log.v("Error", t.toString())
//            }
//        })
//    }
//
//}