package com.monh.packager.data.remote.orders


import com.monh.packager.R
import com.google.gson.annotations.SerializedName
import com.monh.packager.data.remote.products.Product
import com.monh.packager.utils.DAY_MONTH_YEAR
import com.monh.packager.utils.YEAR_MONTH_DAY_T_TIME
import com.monh.packager.utils.toDateFormatted
data class OrderWrapper(@SerializedName("order") val order:Order)
data class Order(
    @SerializedName("orderId")
    val id: Int,
    @SerializedName("shift")
    val orderTime: String?,
    @SerializedName("pickup")
    val orderLocation: String?,
    @SerializedName("totalPrice")
    val amount: Double?,
    @SerializedName("totalItems")
    val numberOfItems: Int?,
    @SerializedName("status")
    val statusName: String?,
    @SerializedName("deliveryTime")
    val orderDate: String?,
    @SerializedName("statusCode")
    val statusId: Int?,
    @SerializedName("isClosed")
    val isClosed:Boolean? = false,
    @SerializedName("items")
    var products:List<Product>? = null
) {
    fun getBackGroundTint(): Int{
        return when(statusId){
            PACKAGING -> R.color.strange_yellow
            else -> R.color.strange_yellow
        }
    }

    fun getTextColor():Int{
        return when(statusId){
            PACKAGING -> R.color.mikadoyellow
            else -> R.color.mikadoyellow
        }
    }

    fun getOrderDateFormatted():String{
        return orderDate?.toDateFormatted(oldPattern = YEAR_MONTH_DAY_T_TIME, newPattern = DAY_MONTH_YEAR)?:""
    }
}
const val ASSIGNED      = 0
const val PACKAGING     = 1
const val SUCCESSFUL    = 2
const val FAILED        = 3
const val IN_PROGRESS   = 4
const val PARTIAL       = 5
const val PENDING       = 6
const val ACCEPTED      = 7
const val DECLINE       = 8
const val CANCEL        = 9
const val DELETED       = 10
const val IGNORED       = 11
const val SEEN_BY_AGENT = 12