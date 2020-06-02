package com.monh.packager.ui.home.my_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.MarginPageTransformer
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class MyOrdersContainerFragment : BaseFragment<MyOrdersContainerViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewPagerAdapter()
        setUpFragmentTitle()
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.my_orders)
    }

    private fun setUpViewPagerAdapter() {
        val ordersPagerAdapter = OrdersPagerAdapter(this)
        with(ordersViewPager){
            clipToPadding = true
            clipChildren = true
            adapter = ordersPagerAdapter
            setPageTransformer(MarginPageTransformer(100))
            // select the open tab by default
            currentItem = 1
        }
        TabLayoutMediator(ordersTabLayOut, ordersViewPager){ tab, position ->
            tab.text = when (position){
                0 -> context?.getString(R.string.urgent)
                1 -> context?.getString(R.string.open)
                else -> context?.getString(R.string.closed)
            }
        }.attach()

    }
}
