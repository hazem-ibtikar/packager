package com.monh.packager.data.remote.products

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.data.remote.orders.OrderWrapper
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.BaseListResponse
import com.monh.packager.utils.network.Services.EndPoints.MARK_ORDER_FOUND
import com.monh.packager.utils.network.Services.EndPoints.MARK_ORDER_UN_FOUND
import com.monh.packager.utils.network.Services.EndPoints.ORDER_PRODUCTS
import com.monh.packager.utils.network.Services.QueryParams.ORDER_ID
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsService {
    @GET(ORDER_PRODUCTS)
    suspend fun getOrderProducts(@Query(ORDER_ID) orderId:Int):Response<BaseResponse<OrderWrapper>>

    @POST(MARK_ORDER_UN_FOUND)
    suspend fun markOrderUnFound(@Body unFoundRequest: UnFoundRequest): Response<BaseResponse<InformativeResponse>>

    @POST(MARK_ORDER_FOUND)
    suspend fun markOrderFound(@Body foundRequest: FoundRequest): Response<BaseResponse<InformativeResponse>>
}