package com.monh.packager.data.remote.orders


import com.monh.packager.R
import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("number_of_items")
    val numberOfItems: Int?,
    @SerializedName("order_date")
    val orderDate: String?,
    @SerializedName("order_location")
    val orderLocation: String?,
    @SerializedName("order_time")
    val orderTime: String?,
    @SerializedName("status_name")
    val statusName: String?,
    @SerializedName("status_id")
    val statusId: String?
){
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
}

const val PACKAGING = "1"