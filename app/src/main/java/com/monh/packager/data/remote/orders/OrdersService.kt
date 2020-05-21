package com.monh.packager.data.remote.orders

import com.monh.packager.utils.network.BaseResponse
import com.monh.packager.data.remote.products.StartOrderRequest
import com.monh.packager.data.remote.products.StartOrderResponse
import com.monh.packager.utils.network.BaseListResponse
import com.monh.packager.utils.network.Services.EndPoints.MARK_AS_PACKAGED
import com.monh.packager.utils.network.Services.EndPoints.ORDERS
import com.monh.packager.utils.network.Services.EndPoints.START_ORDER
import com.monh.packager.utils.network.Services.QueryParams.PAGE
import com.monh.packager.utils.network.Services.QueryParams.STATUS
import retrofit2.Response
import retrofit2.http.*

interface OrdersService {
    @GET(ORDERS)
    suspend fun getUrgentOrders(@Query(STATUS) status:String, @Query(PAGE) page:Int): Response<BaseListResponse<Order>>

    @PUT(START_ORDER)
    suspend fun startNewOrder(@Body startOrderRequest: StartOrderRequest):Response<BaseResponse<StartOrderResponse>>

    @PUT(MARK_AS_PACKAGED)
    suspend fun markAsPackaged(@Body markAsPackaged: MarkAsPackaged) : Response<BaseResponse<OrderPackagedResponse>>
}