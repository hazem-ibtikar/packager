package com.monh.packager.ui.home.my_orders.orders_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monh.packager.R
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.databinding.OrdersItemBinding

class OrdersAdapter(val selectOrder: (Order) -> Unit, var isLastPage:Boolean = true) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        @JvmStatic
        @BindingAdapter("orders")
        fun RecyclerView.bindItems(items: MutableLiveData<List<Order>>?) {
            val adapter = adapter as OrdersAdapter
            items?.observeForever {
                adapter.items = it.toMutableList()
            }
        }
    }

    var items:MutableList<Order> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOAD_MORE_ITEM -> LoadMoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false))
            else ->
                OrderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.orders_item, parent, false
                    )
                )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OrderViewHolder){
            holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == items.size) LOAD_MORE_ITEM else ITEM_VIEW
    }

    override fun getItemCount(): Int = if (isLastPage) items.count() else items.count() + 1


    inner class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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

const val ITEM_VIEW = 1
const val LOAD_MORE_ITEM = 2