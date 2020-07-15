package com.monh.packager.ui.home.my_orders.order_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.data.remote.orders.*
import com.monh.packager.databinding.OrderDetailsFragmentBinding
import com.monh.packager.ui.home.my_orders.order_preparation.OrderPreparationFragmentArgs
import com.monh.packager.utils.EventObserver
import com.monh.packager.utils.network.Result
import com.monh.packager.utils.network.Services
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.order_details_fragment.*
import timber.log.Timber


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
                orderProductsRecyclerView.adapter = orderProductsAdapter
            }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleToolBar()
        viewModel.getOrderProducts(if (viewModel.selectedOrderId == 0)args.orderId else viewModel.selectedOrderId)
        handleStartNewOrder()
        handleOrderDetails()
        hideStartOrderIfClosed()
    }

    private fun hideStartOrderIfClosed() {
        startPrepareBtn.visibility = if (args.isClosed ) View.GONE else View.VISIBLE
    }

    private fun handleOrderDetails() {
        viewModel.orderLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                orderID.text = String.format(getString(R.string.order_id), it.id)
                chip2.text = it.statusName
                chip2.setTextColor(requireContext().getColor(it.getTextColor()))
                orderDate.text = String.format(getString(R.string.order_date_time), it.getOrderDateFormatted(), it.orderTime)
                if (it.orderTime.isNullOrEmpty()){
                    orderDate.visibility  = View.INVISIBLE
                    clock_icon.visibility = View.INVISIBLE
                }
                location_text.text = it.orderLocation
                numberOfItems.text = String.format(getString(R.string.order_quantity), it.numberOfItems)
                price.text = String.format(getString(R.string.order_price), it.amount)
                if (it.statusId == FAILED || it.statusId == DECLINE || it.statusId == CANCEL || it.statusId == DELETED|| it.statusId == IGNORED || it.statusId == SUCCESSFUL || it.statusId == IN_PROGRESS){
                    startPrepareBtn.visibility = View.GONE
                }
            }
        })
    }

    private fun handleStartNewOrder() {
        startPrepareBtn.setOnClickListener {
            viewModel.startNewOrder()
        }
        viewModel.startOrderSuccessfully.observe(viewLifecycleOwner, EventObserver {
            if (it){
                val preparationArgs = OrderPreparationFragmentArgs(orderId = viewModel.selectedOrderId, products = viewModel.orderProductsLiveData.value?.toTypedArray()?: arrayOf())
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

    override fun showError(error: Result.Error) {
        when(error.exception.tag){
            Services.EndPoints.START_ORDER -> {
                try {
                    val orderId = error.exception.extra?.get("order_id").toString().toInt()
                    val snackBar = Snackbar
                        .make(detailsContainerLayOut, error.exception.errorMessage.toString(), Snackbar.LENGTH_LONG)
                        .setAction(R.string.view){
                            viewModel.getOrderProducts(orderId)
                        }
                    snackBar.show()
                }catch (e:Exception){
                    super.showError(error)
                    Timber.e(e)
                }
            }
            else -> super.showError(error)
        }
    }
}
