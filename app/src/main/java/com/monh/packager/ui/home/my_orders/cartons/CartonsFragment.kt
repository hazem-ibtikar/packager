package com.monh.packager.ui.home.my_orders.cartons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.monh.packager.R
import com.monh.packager.base.BaseBottomSheetDialogFragment
import com.monh.packager.ui.home.HomeViewModel
import com.monh.packager.ui.home.my_orders.order_preparation.OrderPreparationViewModel
import kotlinx.android.synthetic.main.add_remove.*
import kotlinx.android.synthetic.main.cartons_fragment.*

class CartonsFragment : BaseBottomSheetDialogFragment<OrderPreparationViewModel>() {

    private lateinit var homeViewModel: HomeViewModel

    var numberOfCartons = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cartons_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
        handleAddRemoveQuantity()
        handleDoneBtn()
        initHomeViewModel()
    }

    private fun initHomeViewModel() {
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private fun handleDoneBtn() {
        submit.setOnClickListener {
            dismiss()
            homeViewModel.setNumberOfCartons(numberOfCartons)
        }
    }

    private fun handleAddRemoveQuantity() {
        addItem.setOnClickListener {
            if (numberOfCartons < 100){
                quantity.text = (++numberOfCartons).toString()
            }
        }

        removeItem.setOnClickListener {
            if (numberOfCartons > 1){
                quantity.text = (--numberOfCartons).toString()
            }
        }
    }

    private fun setupUi() {
        edit.visibility = View.VISIBLE
        pcs.visibility = View.GONE
    }

}
