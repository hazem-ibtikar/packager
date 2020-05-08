package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class OrdersStatistics(
    @SerializedName("daily")
    val dailyOrders: String?,
    @SerializedName("monthly")
    val monthlyOrders: String?
)