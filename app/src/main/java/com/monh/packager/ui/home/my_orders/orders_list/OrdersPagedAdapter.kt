package com.monh.packager.ui.home.my_orders.orders_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.monh.packager.R
import com.monh.packager.base.BasePagedAdapter
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.databinding.OrdersItemBinding

class OrdersPagedAdapter(val selectOrder: (Order) -> Unit, retryCallback: () -> Unit):BasePagedAdapter<Order>(retryCallback, diffCallBack) {
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(
                oldItem: Order,
                newItem: Order
            ): Boolean = newItem == oldItem

            override fun areContentsTheSame(
                oldItem: Order,
                newItem: Order
            ): Boolean = oldItem.id == newItem.id

        }

        @JvmStatic
        @BindingAdapter("orders")
        fun RecyclerView.bindItems(items: MutableLiveData<List<Order>>?) {
            val adapter = adapter as OrdersAdapter
            items?.observeForever {
//                adapter.submitList(it)
            }
        }
    }

    override fun createPagedViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.orders_item, parent, false
            )
        )
    }

    override fun onBindPagedViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        currentList?.get(position)?.let { (holder as OrderViewHolder).bind(it) }
    }

    inner class OrderViewHolder(private var binding: OrdersItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item:Order){
            binding.apply {
                root.apply {
                    setOnClickListener {
                        selectOrder(item)
                    }
                }
                order = item
                executePendingBindings()
            }
        }
    }
}