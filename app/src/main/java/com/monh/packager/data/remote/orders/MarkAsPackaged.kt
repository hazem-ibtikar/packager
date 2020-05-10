package com.monh.packager.data.remote.orders


import com.google.gson.annotations.SerializedName

data class MarkAsPackaged(
    @SerializedName("orderId")
    val orderId: Int?,
    @SerializedName("numCartons")
    val cartons: Int?
)