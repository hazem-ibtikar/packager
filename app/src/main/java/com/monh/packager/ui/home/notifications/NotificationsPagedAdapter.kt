package com.monh.packager.ui.home.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.monh.packager.R
import com.monh.packager.data.remote.seller.Notification
import com.monh.packager.databinding.NotificationsItemBinding
import com.monh.packager.ui.home.my_orders.orders_list.ITEM_VIEW
import com.monh.packager.ui.home.my_orders.orders_list.LOAD_MORE_ITEM

class NotificationsPagedAdapter(val selectNotification: (Notification) -> Unit, var isLastPage:Boolean = true)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    var items:MutableList<Notification> = arrayListOf()

    inner class NotificationViewHolder(private var binding: NotificationsItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item:Notification){
            binding.apply {
                root.apply {
                    setOnClickListener {
                        selectNotification(item)
                    }
                }
                notification = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOAD_MORE_ITEM -> LoadMoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false))
            else ->
                NotificationViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.notifications_item, parent, false
                    )
                )
        }
    }

    override fun getItemCount(): Int = if (isLastPage) items.count() else items.count() + 1


    override fun getItemViewType(position: Int): Int {
        return if(position == items.size) LOAD_MORE_ITEM else ITEM_VIEW
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NotificationViewHolder){
            holder.bind(items[position])
        }
    }
    inner class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}