package com.monh.packager.data.remote.products


import com.google.gson.annotations.SerializedName

data class FoundRequest(
    @SerializedName("orderId")
    val orderId: String?,
    @SerializedName("productID")
    val productID: String?,
    @SerializedName("quantity")
    val quantity: Int?
)