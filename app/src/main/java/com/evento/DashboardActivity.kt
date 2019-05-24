package com.evento

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.api.APIProvider
import com.api.ApiUtils
import com.api.allEvents
import com.api.loginGood
import retrofit2.Callback
import kotlinx.android.synthetic.main.dashboard2.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result.response
import java.lang.reflect.Array


class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var mAPIProvider: APIProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard2)
        // Configure action bar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Evento"

        // Initialize the action bar drawer toggle instance
        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ){
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
                //toast("Drawer closed")
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
                //toast("Drawer opened")
            }
        }


        // Configure the drawer layout to add listener and show icon on toolbar
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        // Set navigation view navigation item selected listener
        navigation_view.setNavigationItemSelectedListener{
            when (it.itemId){
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
            // Close the drawer
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        btn_people.setOnClickListener{
            showToast("click click")
            mAPIProvider = ApiUtils.apiService
            val self = this
            mAPIProvider!!.getEvents().enqueue(object : Callback<allEvents>{
                override fun onResponse(call: Call<allEvents>, response: Response<allEvents>) {
                    if (response.isSuccessful()) {
                        val zoubida = response.body()
                        println("Zoubida!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                        if(zoubida != null) {
                            println(zoubida.data)
                            println(response.body())
                            val intent = Intent(self, EventsActivity::class.java)
                            intent.putExtra("dataAllEvents", zoubida.data)
                            startActivity(intent)
                        }else{
                            showToast("Empty data ( 0 events in BDD )")
                        }
                    }
                }
                override fun onFailure(call: Call<allEvents>, t: Throwable) {
                    showToast("Fail to get all events")
                }

            })
        }

        btn_events.setOnClickListener{
            val self = this
            val intent = Intent(self, EventsListActivity::class.java)
            startActivity(intent)
        }
    }



    // Extension function to show toast message easily
    private fun Context.toast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
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
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
