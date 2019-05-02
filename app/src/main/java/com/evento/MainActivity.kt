package com.evento

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.Toast
import com.api.APIProvider
import com.api.ApiUtils
import com.api.loginGood
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var mAPIProvider: APIProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        mAPIProvider = ApiUtils.apiService

        login_button.setOnClickListener {
            login(edt_email.text.toString(), edt_password.text.toString())
        }
    }

    fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    private fun login(email: String, password: String)  {
        val self = this
        mAPIProvider!!.loginPost(email, password).enqueue(object : Callback<loginGood> {

            override fun onResponse(call: Call<loginGood>, response: Response<loginGood>) {

                if (response.isSuccessful()) {
                    println(response.body())

                    val intent = Intent(self, DashboardActivity::class.java)

                    intent.putExtra("token", "test")
                    startActivity(intent)
                    showToast("Vous êtes bien connecté")
                }
            }

            override fun onFailure(call: Call<loginGood>, t: Throwable) {
                showToast("Erreur de connexion")
            }
        })

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                // Handle the camera action
            }
            R.id.nav_events -> {

            }
            R.id.nav_clients -> {

            }
            R.id.nav_members -> {

            }
            R.id.nav_proches_events -> {

            }
            R.id.nav_about -> {

            }
            R.id.nav_logout -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
