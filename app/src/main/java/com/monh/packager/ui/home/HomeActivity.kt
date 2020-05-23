package com.monh.packager.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.bumptech.glide.Glide
import com.monh.packager.R
import com.monh.packager.base.BaseActivity
import com.monh.packager.ui.home.my_orders.order_details.OrderDetailsFragmentArgs
import com.monh.packager.utils.EventObserver
import com.monh.packager.utils.MessageUtils
import com.monh.packager.utils.network.Result
import com.monh.packager.utils.network.Services.EndPoints.CHANGE_STATUS
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.home_drawer_nav_view.*


class HomeActivity : BaseActivity<HomeViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration

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
        checkIfFirebaseNotification(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        checkIfFirebaseNotification(intent)
    }

    private fun checkIfFirebaseNotification(intent: Intent?) {
        intent?.getIntExtra(ORDER_ID, 0).let {
            if (it != 0 && it != null){
                val args = OrderDetailsFragmentArgs(it, false)
                navigate(R.id.orderDetailsFragment, args.toBundle())
            }
        }
    }

    private fun handleChangeStatus() {
        viewModel.statusOnline.observe(this, Observer {
            packagerStatus.isChecked = it
        })

        packagerStatus.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeStatus(isChecked)
            status_online_header.text = if (isChecked) getString(R.string.status_online) else getString(R.string.status_offline)
        }

        viewModel.userMessage.observe(this, EventObserver{
            MessageUtils.showSuccessMessage(this, getString(it))
        })
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
                Glide.with(this).load(it.packager?.imageUrl).into(userImage)
                userName.text = it.packager?.name
                userId.text = String.format(getString(R.string.userId), it.packager?.id.toString())
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

    private fun navigate(destinationId:Int, bundle: Bundle? = null){
        drawer_layout.close()
        val navController = findNavController(R.id.nav_host_fragment)
        val builder = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.nav_my_orders, false)
        val options = builder.build()
        navController.navigate(destinationId, bundle, options)

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

    override fun showError(error: Result.Error) {
        super.showError(error)
        when(error.exception.tag){
            CHANGE_STATUS -> {
                // error in change status
                packagerStatus.isChecked = !packagerStatus.isChecked
            }
        }
    }

}

const val ORDER_ID = "OrderId"