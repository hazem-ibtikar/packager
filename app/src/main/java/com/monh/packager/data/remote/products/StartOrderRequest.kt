package com.monh.packager.data.remote.products


import com.google.gson.annotations.SerializedName

data class StartOrderRequest(
    @SerializedName("order_id")
    val orderId: String?
)