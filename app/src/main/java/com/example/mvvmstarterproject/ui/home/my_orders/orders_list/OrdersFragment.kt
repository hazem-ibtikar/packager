package com.example.mvvmstarterproject.ui.home.my_orders.orders_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import com.example.mvvmstarterproject.data.remote.orders.Order
import com.example.mvvmstarterproject.databinding.OrdersFragmentBinding

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
                ORDER_URGENT -> viewModel.getUrgentOrders()
                ORDER_OPEN -> viewModel.getOpenOrders()
                else -> viewModel.getClosedOrders()
            }
        }
    }
    private fun navigateToOrderDetails(order: Order){

    }

}
const val ORDER_TYPE = "order type"
const val ORDER_URGENT = 1
const val ORDER_OPEN = 2
const val ORDER_CLOSED = 3