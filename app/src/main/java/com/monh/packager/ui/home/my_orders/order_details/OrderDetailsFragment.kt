package com.monh.packager.ui.home.my_orders.order_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.databinding.OrderDetailsFragmentBinding
import com.monh.packager.ui.home.my_orders.order_preparation.OrderPreparationFragmentArgs
import com.monh.packager.utils.EventObserver
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.order_details_fragment.*

class OrderDetailsFragment : BaseFragment<OrderDetailsViewModel>() {

    private val args: OrderDetailsFragmentArgs by navArgs()
    private val orderProductsAdapter = OrderProductsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<OrderDetailsFragmentBinding>(inflater, R.layout.order_details_fragment, container, false)
            .apply {
                lifecycleOwner = this@OrderDetailsFragment
                vm = viewModel
                order = args.order
                orderProductsRecyclerView.adapter = orderProductsAdapter
            }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleToolBar()
        viewModel.getOrderProducts(args.order.id!!.toInt())
        handleStartNewOrder()
    }

    private fun handleStartNewOrder() {
        startPrepareBtn.setOnClickListener {
            viewModel.startNewOrder(args.order.id!!.toInt())
        }
        viewModel.startOrderSuccessfully.observe(viewLifecycleOwner, EventObserver {
            if (it){
                val preparationArgs = OrderPreparationFragmentArgs(order = args.order, products = viewModel.orderProductsLiveData.value?.toTypedArray()?: arrayOf())
                // navigate to order preparation
                findNavController().navigate(R.id.action_orderDetailsFragment_to_orderPreparationFragment, preparationArgs.toBundle())
            }
        })
    }


    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.icon_back)
        activity?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = ""
    }

}
