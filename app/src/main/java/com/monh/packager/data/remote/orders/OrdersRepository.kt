package com.monh.packager.data.remote.orders

import com.monh.packager.base.BaseRepository
import com.monh.packager.data.remote.products.StartOrderRequest
import com.monh.packager.data.remote.products.StartOrderResponse
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val ordersService: OrdersService
): BaseRepository() {

    suspend fun getOrders(orderType:String, page: Int): Result<List<Order>> {
        return safeApiCall { ordersService.getUrgentOrders(orderType, page) }
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


    suspend fun startNewOrder(orderId:Int):Result<StartOrderResponse>{
        return safeApiCall { ordersService.startNewOrder(StartOrderRequest(orderId)) }
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

    suspend fun markAsPackaged(markAsPackaged: MarkAsPackaged): Result<OrderPackagedResponse>{
        return safeApiCall { ordersService.markAsPackaged(markAsPackaged) }
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