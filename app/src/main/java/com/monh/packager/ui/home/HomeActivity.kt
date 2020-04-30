package com.monh.packager.ui.home

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.bumptech.glide.Glide
import com.monh.packager.R
import com.monh.packager.base.BaseActivity
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
        viewModel.getUserInfo()
        handleUserInfo()
        viewModel.getOrdersStatistics()
        handleOrdersStatistics()
        viewModel.getStatusOnline()
        handleChangeStatus()
    }

    private fun handleChangeStatus() {
        viewModel.statusOnline.observe(this, Observer {
            packagerStatus.isChecked = it
        })
        packagerStatus.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeStatus(isChecked)
        }
    }

    private fun handleOrdersStatistics() {
        viewModel.ordersStatisticsLiveData.observe(this, Observer {
            it?.let {
                monthly_order_count.text = it.monthlyOrders
                daily_orders_count.text = it.dailyOrders
            }
        })
    }

    private fun handleUserInfo() {
        viewModel.userLiveData.observe(this, Observer {
            if (it != null){
                Glide.with(this).load(it.image).into(userImage)
                userName.text = it.englishFullName
                userId.text = String.format(getString(R.string.userId), it.id)
            }
        })
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
