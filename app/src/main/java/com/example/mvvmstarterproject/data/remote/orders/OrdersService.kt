package com.example.mvvmstarterproject.data.remote.orders

import com.example.mvvmstarterproject.utils.network.BaseListResponse
import com.example.mvvmstarterproject.utils.network.Services.EndPoints.CLOSED_ORDERS
import com.example.mvvmstarterproject.utils.network.Services.EndPoints.OPEN_ORDERS
import com.example.mvvmstarterproject.utils.network.Services.EndPoints.URGENT_ORDERS
import retrofit2.Response
import retrofit2.http.GET

interface OrdersService {
    @GET(URGENT_ORDERS)
    suspend fun getUrgentOrders(): Response<BaseListResponse<Order>>

    @GET(OPEN_ORDERS)
    suspend fun getOpenOrders(): Response<BaseListResponse<Order>>

    @GET(CLOSED_ORDERS)
    suspend fun getClosedOrders(): Response<BaseListResponse<Order>>
}