package com.monh.packager.ui.home.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monh.packager.R
import kotlinx.android.synthetic.main.app_bar_home.*
import com.monh.packager.base.BaseFragment
import com.monh.packager.data.remote.seller.Notification
import com.monh.packager.databinding.FragmentGalleryBinding
import com.monh.packager.ui.home.my_orders.order_details.OrderDetailsFragmentArgs
import com.monh.packager.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.swipeToRefreshOrders

class NotificationsFragment : BaseFragment<NotificationsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentGalleryBinding>(inflater, R.layout.fragment_gallery, container, false).apply{
            lifecycleOwner = this@NotificationsFragment
            vm = viewModel
        }.root
    }

    private val adapter = NotificationsPagedAdapter(::selectNotification)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleNotifications()
        viewModel.getNotifications()
        addSwipeToRefresh()
        addLoadMoreListener()
    }

    private fun addLoadMoreListener() {
        val listLinearLayoutManager = notificationsRecyclerView.layoutManager as LinearLayoutManager

        notificationsRecyclerView.addOnScrollListener(object : PaginationScrollListener(listLinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage.value?:false
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                viewModel.getNotifications()
            }
        })

        viewModel.isLastPage.observe(viewLifecycleOwner, Observer {
            if (it != null){
                adapter.isLastPage = it
                adapter.notifyDataSetChanged()
            }
        })
    }
    private fun addSwipeToRefresh() {
        swipeToRefreshOrders.setOnRefreshListener {
            viewModel.getNotifications(isReset = true)
        }
    }
    private fun handleNotifications() {
        viewModel.notificationsLiveData.observe(viewLifecycleOwner, Observer{
            swipeToRefreshOrders.isRefreshing = false
            notificationsRecyclerView.adapter = adapter
            adapter.items = it

            if (it.size > 0){
                no_notifications_layout.visibility = View.GONE
            }else{
                no_notifications_layout.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.notifications)
    }

    fun selectNotification(notification: Notification){
        val args = OrderDetailsFragmentArgs(339127)
        findNavController().navigate(R.id.action_nav_notifications_to_orderDetailsFragment, args.toBundle())
    }
}
