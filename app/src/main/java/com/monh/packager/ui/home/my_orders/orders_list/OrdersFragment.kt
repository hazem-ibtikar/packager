package com.monh.packager.ui.home.my_orders.orders_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.databinding.OrdersFragmentBinding
import com.monh.packager.ui.home.my_orders.order_details.OrderDetailsFragmentArgs
import com.monh.packager.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.orders_fragment.*

class OrdersFragment : BaseFragment<OrdersViewModel>() {

    companion object {
        fun newInstance(orderType:Int) : OrdersFragment{
            return OrdersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ORDER_TYPE, orderType)
                }
            }
        }
    }

    private val adapter = OrdersAdapter(::navigateToOrderDetails)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<OrdersFragmentBinding>(
            inflater, R.layout.orders_fragment, container, false
        ).apply{
            lifecycleOwner = this@OrdersFragment
            vm = viewModel
            ordersRecyclerView.adapter = adapter
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getInt(ORDER_TYPE).also {
            when(it){
                ORDER_URGENT -> viewModel.getOrders(URGENT, isReset = true)
                ORDER_OPEN -> viewModel.getOrders(OPEN, isReset = true)
                else -> viewModel.getOrders(CLOSED, isReset = true)
            }
        }
        addLoadMoreListener()
        handleOrders()
        addSwipeToRefresh()
        setUpFragmentTitle()
        handleToolBar()
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.my_orders)
    }

    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.ic_sidemenu)
        activity?.toolbar?.menu?.clear()
        activity?.toolbar?.setNavigationOnClickListener {
            activity?.drawer_layout?.open()
        }
    }

    private fun addSwipeToRefresh() {
        swipeToRefreshOrders.setOnRefreshListener {
            arguments?.getInt(ORDER_TYPE).also {
                when(it){
                    ORDER_URGENT -> viewModel.getOrders(URGENT, true)
                    ORDER_OPEN -> viewModel.getOrders(OPEN, true)
                    else -> viewModel.getOrders(CLOSED, true)
                }
            }
        }
    }

    private fun handleOrders() {
        viewModel.ordersLiveData.observe(viewLifecycleOwner, Observer {
            swipeToRefreshOrders.isRefreshing = false
            adapter.items = it
            if (it?.size == 0){
                no_orders_layout.visibility = View.VISIBLE
            }else {
                no_orders_layout.visibility = View.GONE
            }
        })
    }

    private fun addLoadMoreListener() {
        val listLinearLayoutManager = ordersRecyclerView.layoutManager as LinearLayoutManager

        ordersRecyclerView.addOnScrollListener(object : PaginationScrollListener(listLinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage.value?:false
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                arguments?.getInt(ORDER_TYPE).also {
                    when(it){
                        ORDER_URGENT -> viewModel.getOrders(URGENT)
                        ORDER_OPEN -> viewModel.getOrders(OPEN)
                        else -> viewModel.getOrders(CLOSED)
                    }
                }
            }
        })

        viewModel.isLastPage.observe(viewLifecycleOwner, Observer {
            if (it != null){
                adapter.isLastPage = it
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun navigateToOrderDetails(order: Order){
        val args = OrderDetailsFragmentArgs(order.id,ORDER_CLOSED == arguments?.getInt(ORDER_TYPE, 0))
        findNavController().navigate(R.id.action_nav_my_orders_to_orderDetailsFragment, args.toBundle())
    }

}
const val ORDER_TYPE = "order type"
const val ORDER_URGENT = 1
const val ORDER_OPEN = 2
const val ORDER_CLOSED = 3

const val URGENT = "urgent"
const val OPEN   = "open"
const val CLOSED = "closed"