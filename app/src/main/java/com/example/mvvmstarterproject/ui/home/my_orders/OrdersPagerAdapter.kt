package com.example.mvvmstarterproject.ui.home.my_orders

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvvmstarterproject.ui.home.my_orders.orders_list.ORDER_CLOSED
import com.example.mvvmstarterproject.ui.home.my_orders.orders_list.ORDER_OPEN
import com.example.mvvmstarterproject.ui.home.my_orders.orders_list.ORDER_URGENT
import com.example.mvvmstarterproject.ui.home.my_orders.orders_list.OrdersFragment

class OrdersPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OrdersFragment.newInstance(ORDER_URGENT)
            1 -> OrdersFragment.newInstance(ORDER_OPEN)
            2 -> OrdersFragment.newInstance(ORDER_CLOSED)
            else -> Fragment()
        }
    }
}