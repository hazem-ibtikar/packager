package com.monh.packager.ui.home.my_orders.found_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.databinding.OrderPreprationItemBinding

class FoundOrderFragment : BaseFragment<FoundOrderViewModel>() {

    private val args: FoundOrderFragmentArgs by navArgs()
    companion object {
        fun newInstance() = FoundOrderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<OrderPreprationItemBinding>(inflater, R.layout.order_prepration_item, container, false)
            .apply {
                lifecycleOwner = this@FoundOrderFragment
                product = args.product
            }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
