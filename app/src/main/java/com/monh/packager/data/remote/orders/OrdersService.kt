package com.monh.packager.data.remote.orders

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.data.remote.products.StartOrderRequest
import com.monh.packager.data.remote.products.StartOrderResponse
import com.monh.packager.utils.network.BaseListResponse
import com.monh.packager.utils.network.Services
import com.monh.packager.utils.network.Services.EndPoints.CLOSED_ORDERS
import com.monh.packager.utils.network.Services.EndPoints.OPEN_ORDERS
import com.monh.packager.utils.network.Services.EndPoints.URGENT_ORDERS
import com.monh.packager.utils.network.Services.QueryParams.PAGE
import com.monh.packager.utils.network.Services.QueryParams.STATUS
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrdersService {
    @GET(URGENT_ORDERS)
    suspend fun getUrgentOrders(@Query(STATUS) status:String, @Query(PAGE) page:Int): Response<BaseListResponse<Order>>

    @GET(OPEN_ORDERS)
    suspend fun getOpenOrders(): Response<BaseListResponse<Order>>

    @GET(CLOSED_ORDERS)
    suspend fun getClosedOrders(): Response<BaseListResponse<Order>>

    @POST(Services.EndPoints.START_ORDER)
    suspend fun startNewOrder(@Body startOrderRequest: StartOrderRequest):Response<BaseResponse<StartOrderResponse>>
}