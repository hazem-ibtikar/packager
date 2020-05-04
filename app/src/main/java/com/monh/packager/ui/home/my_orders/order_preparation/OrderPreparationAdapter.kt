package com.monh.packager.ui.home.my_orders.order_preparation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monh.packager.R
import com.monh.packager.data.remote.products.Product
import com.monh.packager.databinding.OrderPreprationItemBinding

class OrderPreparationAdapter : ListAdapter<Product, RecyclerView.ViewHolder>(diffCallBack) {
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean = newItem == oldItem

            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean = oldItem.id == newItem.id

        }

        @JvmStatic
        @BindingAdapter("productsPreparation")
        fun RecyclerView.bindItems(items: MutableLiveData<List<Product>>?) {
            val adapter = adapter as OrderPreparationAdapter
            items?.observeForever {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.order_prepration_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OrderViewHolder).bind(currentList[position])
    }

    inner class OrderViewHolder(private var binding: OrderPreprationItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item:Product){
            binding.apply {
                product = item
                executePendingBindings()
            }
        }
    }
}