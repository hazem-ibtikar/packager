package com.monh.packager.ui.home.my_orders.cartons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.monh.packager.R
import com.monh.packager.base.BaseBottomSheetDialogFragment
import com.monh.packager.ui.home.my_orders.order_preparation.OrderPreparationViewModel
import kotlinx.android.synthetic.main.add_remove.*
import kotlinx.android.synthetic.main.cartons_fragment.*

class CartonsFragment : BaseBottomSheetDialogFragment<OrderPreparationViewModel>() {

    companion object {
        fun newInstance() = CartonsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cartons_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        edit.visibility = View.VISIBLE
        pcs.visibility = View.GONE
    }

}
