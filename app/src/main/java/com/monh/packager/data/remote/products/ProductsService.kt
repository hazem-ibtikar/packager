package com.monh.packager.data.remote.products

import com.monh.packager.utils.network.BaseListResponse
import com.monh.packager.utils.network.Services.EndPoints.ORDER_PRODUCTS
import com.monh.packager.utils.network.Services.QueryParams.ORDER_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {
    @GET(ORDER_PRODUCTS)
    suspend fun getOrderProducts(@Query(ORDER_ID) orderId:Int):Response<BaseListResponse<Product>>
}