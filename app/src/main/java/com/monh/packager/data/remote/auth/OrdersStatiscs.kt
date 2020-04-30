package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class OrdersStatistics(
    @SerializedName("daily_orders")
    val dailyOrders: String?,
    @SerializedName("monthly_orders")
    val monthlyOrders: String?
)