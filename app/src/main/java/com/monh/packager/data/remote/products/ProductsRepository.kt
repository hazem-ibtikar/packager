package com.monh.packager.data.remote.products

import com.monh.packager.base.BaseRepository
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService
) :BaseRepository() {
    suspend fun getOrderProducts(orderId:Int): Result<Order>{
        return safeApiCall { productsService.getOrderProducts(orderId) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data?.order!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun markOrderUnFound(productId: Int): Result<InformativeResponse>{
        return safeApiCall { productsService.markOrderUnFound(productId) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun markOrderFound(foundRequest: FoundRequest): Result<InformativeResponse>{
        return safeApiCall { productsService.markOrderFound(foundRequest) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

}