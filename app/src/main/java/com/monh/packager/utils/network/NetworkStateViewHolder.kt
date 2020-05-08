package com.monh.packager.utils.network

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.network_state_list_item.view.*

class NetworkStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(retryCallback: () -> Unit, networkState: NetworkState?) {
        with(itemView) {
            loadingIndicator.visibility =
                if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
            retryBtn.visibility =
                if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
            errorMessageTV.visibility =
                if (networkState?.errorMessageRes != null) View.VISIBLE else View.GONE
            if (networkState?.errorMessageRes != null) {
                errorMessageTV.text = resources.getString(networkState.errorMessageRes)
            }
            retryBtn.setOnClickListener {
                retryCallback()
            }
        }
    }
}
