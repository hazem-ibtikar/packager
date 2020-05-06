package com.monh.packager.ui.home.my_orders.found_order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.zxing.integration.android.IntentIntegrator
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.databinding.OrderPreprationItemBinding
import com.monh.packager.ui.home.HomeViewModel
import com.monh.packager.utils.toPixels
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.order_prepration_item.*


class FoundOrderFragment : BaseFragment<FoundOrderViewModel>() {
    private val args: FoundOrderFragmentArgs by navArgs()
    private lateinit var homeViewModel:HomeViewModel
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
        setupUI()
        initViewModel()
        handleFoundBtnClick()
        handleAddRemoveQuantity()
        handleMarkedAsFound()
        initHomeViewModel()
    }

    private fun initHomeViewModel() {
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    private fun handleMarkedAsFound() {
        viewModel.markFoundedSuccessfully.observe(viewLifecycleOwner, Observer {
            homeViewModel.markProductAsFound(viewModel.product)
            findNavController().popBackStack()
        })
    }

    private fun handleAddRemoveQuantity() {
        addItem.setOnClickListener {
            viewModel.addQuantity()
        }
        removeItem.setOnClickListener {
            viewModel.removeQuantity()
        }
        viewModel.foundedQuantityLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                quantity.text = it.toString()
            }
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                showErrorMsg(it)
            }
        })
    }

    private fun handleFoundBtnClick() {
        foundBtn.setOnClickListener {
            viewModel.markProductAsFound()
        }
    }

    private fun initViewModel() {
        viewModel.product = args.product
        viewModel.order = args.order
    }

    private fun setupUI() {
        // add some margin
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(15.0f.toPixels(requireContext()), 15.0f.toPixels(requireContext()), 15.0f.toPixels(requireContext()), 15.0f.toPixels(requireContext()))
        container.layoutParams = params

        // show the add remove quantity
        update_quantity.visibility = View.VISIBLE

        // hide the un found btn
        unFoundEditBtn.visibility = View.INVISIBLE

        activity?.toolbar?.inflateMenu(R.menu.main_tool_bar)
        activity?.toolbar?.setOnMenuItemClickListener {
            if (it.itemId == R.id.barCode){
                val barCodeScanner = IntentIntegrator.forSupportFragment(this)
                barCodeScanner.setOrientationLocked(false)
                barCodeScanner.initiateScan()
            }
            return@setOnMenuItemClickListener false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                viewModel.checkItemFromBarCode(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
