package com.example.mvvmstarterproject.ui.home

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.home_drawer_nav_view.*


class HomeActivity : BaseActivity<HomeViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var currentIndex : Int = R.id.nav_my_orders

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        handleToolBar()
        setUpDrawerIconsWithNavigation()
    }

    private fun setUpDrawerIconsWithNavigation() {
        my_orders.setOnClickListener {
            navigate(R.id.nav_my_orders)
        }
        my_orders_header.setOnClickListener {
            navigate(R.id.nav_my_orders)
        }

        notifications.setOnClickListener {
            navigate(R.id.nav_notifications)
        }
        notifications_header.setOnClickListener {
            navigate(R.id.nav_notifications)
        }

        settings.setOnClickListener {
            navigate(R.id.nav_settings)
        }
        settings_header.setOnClickListener {
            navigate(R.id.nav_settings)
        }

    }

    private fun navigate(destinationId:Int){
        drawer_layout.close()
        if (destinationId != currentIndex){
            currentIndex = destinationId
            val navController = findNavController(R.id.nav_host_fragment)
            val builder = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(R.id.nav_my_orders, false)
            val options = builder.build()
            navController.navigate(destinationId, null, options)
        }

    }
    private fun handleToolBar() {
        toolbar.setNavigationOnClickListener {
            drawer_layout.open()
        }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
