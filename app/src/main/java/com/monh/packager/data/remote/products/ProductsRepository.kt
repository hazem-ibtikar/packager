package com.monh.packager.data.remote.products

import com.monh.packager.base.BaseRepository
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService
) :BaseRepository() {
    suspend fun getOrderProducts(orderId:Int): Result<List<Product>>{
        return safeApiCall { productsService.getOrderProducts(orderId) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data?.list!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }
}