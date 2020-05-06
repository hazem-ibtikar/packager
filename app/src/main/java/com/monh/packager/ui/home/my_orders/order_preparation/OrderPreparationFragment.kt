package com.monh.packager.ui.home.my_orders.order_preparation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.data.remote.products.Product
import com.monh.packager.databinding.OrderPreparationFragmentBinding
import com.monh.packager.ui.home.my_orders.found_order.FoundOrderFragment
import com.monh.packager.ui.home.my_orders.found_order.FoundOrderFragmentArgs
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.order_details_fragment.*

class OrderPreparationFragment : BaseFragment<OrderPreparationViewModel>() {

    private val args: OrderPreparationFragmentArgs by navArgs()
    private val orderPreparationAdapter = OrderPreparationAdapter(::handleProductClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<OrderPreparationFragmentBinding>(inflater, R.layout.order_preparation_fragment, container, false)
            .apply {
                lifecycleOwner = this@OrderPreparationFragment
                vm = viewModel
                ProductsPreparationRecyclerView.adapter = orderPreparationAdapter
            }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.addList(args.products)
        setUpFragmentTitle()
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = String.format(getString(R.string.orderId), args.order.id)
    }

    private fun handleProductClick(product:Product, action:Int){
        if (action == FOUND){
            // navigate to found product screen
            val args = FoundOrderFragmentArgs(order = args.order, product = product)
            findNavController().navigate(R.id.action_orderPreparationFragment_to_foundOrderFragment, args.toBundle())
        } else if (action == UN_FOUNd){
            viewModel.markProductAsUnFound(product.id!!.toInt(), args.order.id?:"")
        }
    }
}
