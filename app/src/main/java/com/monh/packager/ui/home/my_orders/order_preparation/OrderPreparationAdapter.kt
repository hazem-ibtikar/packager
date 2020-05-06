package com.monh.packager.ui.home.my_orders.order_preparation

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
import com.monh.packager.data.remote.products.Product
import com.monh.packager.databinding.OrderPreprationItemBinding

class OrderPreparationAdapter(var selectProduct: (product:Product, action:Int) -> Unit) : ListAdapter<Product, RecyclerView.ViewHolder>(diffCallBack) {
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean = newItem == oldItem

            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean = (oldItem.id == newItem.id) && (oldItem.isFound == newItem.isFound) && (oldItem.isNotFound == newItem.isNotFound)

        }

        @JvmStatic
        @BindingAdapter("productsPreparation")
        fun RecyclerView.bindItems(items: MutableLiveData<List<Product>>?) {
            val adapter = adapter as OrderPreparationAdapter
            items?.observeForever {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
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
                this.root.apply {
                    cellHeader.text = item.categoryName + " (" + currentList.toMutableList().filter { it.categoryId == item.categoryId }.size + ")"
                    val currentIndex = currentList.indexOf(item)
                    if (currentIndex > 0){
                        cellHeader.visibility = if (currentList[currentIndex].categoryId != currentList[currentIndex - 1].categoryId){
                            View.VISIBLE
                        }else {View.GONE}
                    }
                    unFoundEditBtn.setOnClickListener {
                        if (item.isFound == true || item.isNotFound == true){
                            // edit btn clicked
                            item.isFound = false
                            item.isNotFound = false
                            notifyDataSetChanged()
                        }else {
                            // un found btn clicked
                            selectProduct(item, UN_FOUNd)
                        }
                    }
                    foundBtn.setOnClickListener {
                        if (item.isFound == true || item.isNotFound == true){
                            // added/not added btn click
                        }else{
                            // found btn clicked
                            selectProduct(item, FOUND)
                        }
                    }
                }
                product = item
                executePendingBindings()
            }
        }
    }
}
const val FOUND = 1
const val UN_FOUNd = 2
const val EDIT = 3