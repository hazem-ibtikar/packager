package com.monh.packager.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.monh.packager.R
import com.monh.packager.utils.network.NetworkState
import com.monh.packager.utils.network.NetworkStateViewHolder

abstract class BasePagedAdapter<T>(private val retryCallback: () -> Unit, r:DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, RecyclerView.ViewHolder>(r)  {

    private var networkState: NetworkState? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIST_ITEM -> {
                createPagedViewHolder(parent, viewType)
            }
            LOAD_MORE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.network_state_list_item, parent, false)
                NetworkStateViewHolder(view)
            }
            else -> {
                throw IllegalArgumentException("unknown view type $viewType")
            }
        }
    }

    abstract fun createPagedViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            LOAD_MORE
        } else {
            LIST_ITEM
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            LIST_ITEM -> {
                onBindPagedViewHolder(holder, position)
            }
            LOAD_MORE -> {
                (holder as NetworkStateViewHolder).bind(retryCallback, networkState)
            }
        }
    }

    abstract fun onBindPagedViewHolder(holder: RecyclerView.ViewHolder, position: Int)
}

const val LIST_ITEM = 1
const val LOAD_MORE = 2

