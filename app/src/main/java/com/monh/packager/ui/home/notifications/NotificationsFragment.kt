package com.monh.packager.ui.home.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.monh.packager.R
import kotlinx.android.synthetic.main.app_bar_home.*
import com.monh.packager.base.BaseFragment
import com.monh.packager.base.BasePagedAdapter
import com.monh.packager.base.BasePagedFragment
import com.monh.packager.data.remote.seller.Notification
import com.monh.packager.databinding.FragmentGalleryBinding
import com.monh.packager.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.swipeToRefreshOrders
import kotlinx.android.synthetic.main.orders_fragment.*

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

            if (it.size > 0){
                notificationsRecyclerView.adapter = adapter
                adapter.items = it
            }
        })
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.notifications)
    }

    fun selectNotification(notification: Notification){

    }
}
