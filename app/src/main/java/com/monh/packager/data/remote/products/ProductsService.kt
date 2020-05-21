package com.monh.packager.data.remote.products

import com.monh.packager.utils.network.BaseResponse
import com.monh.packager.data.remote.orders.OrderWrapper
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.Services.EndPoints.MARK_ORDER_FOUND
import com.monh.packager.utils.network.Services.EndPoints.MARK_ORDER_UN_FOUND
import com.monh.packager.utils.network.Services.EndPoints.ORDER_PRODUCTS
import com.monh.packager.utils.network.Services.QueryParams.ORDER_ID
import com.monh.packager.utils.network.Services.QueryParams.PRODUCT_IN_ORDER
import retrofit2.Response
import retrofit2.http.*

interface ProductsService {
    @GET(ORDER_PRODUCTS)
    suspend fun getOrderProducts(@Query(ORDER_ID) orderId:Int):Response<BaseResponse<OrderWrapper>>

    @PUT(MARK_ORDER_UN_FOUND)
    suspend fun markOrderUnFound(@Query(PRODUCT_IN_ORDER) productId: Int): Response<BaseResponse<InformativeResponse>>

    @PUT(MARK_ORDER_FOUND)
    suspend fun markOrderFound(@Body foundRequest: FoundRequest): Response<BaseResponse<InformativeResponse>>
}