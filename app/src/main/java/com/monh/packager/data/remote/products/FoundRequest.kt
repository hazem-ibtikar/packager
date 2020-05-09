package com.monh.packager.data.remote.products


import com.google.gson.annotations.SerializedName

data class FoundRequest(
    @SerializedName("orderId")
    val orderId: String?,
    @SerializedName("orderItemId")
    val productID: Int,
    @SerializedName("foundCount")
    val quantity: Int
)