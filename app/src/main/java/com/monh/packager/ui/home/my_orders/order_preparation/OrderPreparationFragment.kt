package com.monh.packager.ui.home.my_orders.order_preparation

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.data.remote.products.Product
import com.monh.packager.databinding.OrderPreparationFragmentBinding
import com.monh.packager.ui.home.HomeViewModel
import com.monh.packager.ui.home.my_orders.found_order.FoundOrderFragmentArgs
import com.monh.packager.utils.EventObserver
import com.monh.packager.utils.storeToPdfAndOpen
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.order_preparation_fragment.*

class OrderPreparationFragment : BaseFragment<OrderPreparationViewModel>() {

    private val args: OrderPreparationFragmentArgs by navArgs()
    private val orderPreparationAdapter = OrderPreparationAdapter(::handleProductClick)

    private lateinit var homeViewModel: HomeViewModel

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
        initHomeViewModel()
        handleFoundedProduct()
        handleDoneBtn()
        sendOrderAfterSelectingNumberOfCartons()
        handleOrderSendSuccessfully()
    }

    private fun handleOrderSendSuccessfully() {
        viewModel.orderPackagedLiveData.observe(viewLifecycleOwner, Observer {
            getPermissionAndSavePdf(it.base64Pdf?:"")
        })
    }

    private fun getPermissionAndSavePdf(base64Pdf:String) {
        Dexter.withActivity(activity)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report?.areAllPermissionsGranted()!!){
                        storeToPdfAndOpen(requireContext(), base64Pdf)
                    } else{
                        Toast.makeText(activity, "we need to download pdf to be able to print it", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {}

            }).check()
    }

    private fun sendOrderAfterSelectingNumberOfCartons() {
        homeViewModel.numberOfCartonsLiveData.observe(viewLifecycleOwner, EventObserver{
            viewModel.markOrderAsPackaged(args.orderId, it)
        })
    }

    private fun handleDoneBtn() {
        doneBtn.setOnClickListener {
            findNavController().navigate(R.id.action_orderPreparationFragment_to_cartonsFragment)
        }
    }

    private fun handleFoundedProduct() {
        homeViewModel.foundedProduct.observe(viewLifecycleOwner, EventObserver{
            viewModel.markProductAsFound(it.id!!.toInt())
        })
    }

    private fun initHomeViewModel() {
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = String.format(getString(R.string.orderId), args.orderId.toString())
    }

    private fun handleProductClick(product:Product, action:Int){
        if (action == FOUND){
            // navigate to found product screen
            val args = FoundOrderFragmentArgs(orderId = args.orderId, product = product)
            findNavController().navigate(R.id.action_orderPreparationFragment_to_foundOrderFragment, args.toBundle())
        } else if (action == UN_FOUNd){
            viewModel.markProductAsUnFound(product.order_item_id, args.orderId.toString())
        }
    }
}
