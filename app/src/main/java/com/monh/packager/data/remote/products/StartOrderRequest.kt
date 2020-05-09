package com.monh.packager.data.remote.products


import com.google.gson.annotations.SerializedName

data class StartOrderRequest(
    @SerializedName("orderId")
    val orderId: Int
)