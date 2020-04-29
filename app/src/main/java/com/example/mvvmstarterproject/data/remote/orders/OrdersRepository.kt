package com.example.mvvmstarterproject.data.remote.orders

import com.example.mvvmstarterproject.base.BaseRepository
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val ordersService: OrdersService
): BaseRepository() {
    suspend fun getUrgentOrders(): Result<List<Order>> {
        return safeApiCall { ordersService.getUrgentOrders() }
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

    suspend fun getOpenOrders(): Result<List<Order>> {
        return safeApiCall { ordersService.getOpenOrders() }
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

    suspend fun getClosedOrders(): Result<List<Order>> {
        return safeApiCall { ordersService.getClosedOrders() }
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