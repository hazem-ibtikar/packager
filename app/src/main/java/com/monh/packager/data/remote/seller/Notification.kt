package com.monh.packager.data.remote.seller


import com.google.gson.annotations.SerializedName
import com.monh.packager.utils.DAY_MONTH_YEAR_HOUR_MIN
import com.monh.packager.utils.YEAR_MONTH_DAY_T_TIME
import com.monh.packager.utils.toDateFormatted

data class NotificationWrapper(@SerializedName("notifications") val notifications: List<Notification> )
data class Notification(

    @SerializedName("id")
    val id: Int?,
    @SerializedName("body")
    val message: String?,
    @SerializedName("created_at")
    val time: String?,
    @SerializedName("order_id")
    val orderId:Int,
    @SerializedName("seller_id")
    val sellerId:Int
){
    fun getTimeLocalized():String{
        return time?.toDateFormatted(YEAR_MONTH_DAY_T_TIME, DAY_MONTH_YEAR_HOUR_MIN)?:""
    }
}