package com.monh.packager.data.remote.orders


import android.os.Parcelable
import com.monh.packager.R
import com.google.gson.annotations.SerializedName
import com.monh.packager.utils.DAY_MONTH_YEAR
import com.monh.packager.utils.YEAR_MONTH_DAY_T_TIME
import com.monh.packager.utils.toDateFormatted
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    @SerializedName("status_id")
    val statusId: String?
) : Parcelable {
    fun getBackGroundTint(): Int{
        return when(statusId){
            PACKAGING -> R.color.strange_yellow
            else -> R.color.strange_yellow
        }
    }

    fun getTextColor():Int{
        return when(statusName){
            PACKAGING -> R.color.mikadoyellow
            else -> R.color.mikadoyellow
        }
    }

    fun getOrderDateFormatted():String{
        return orderDate?.toDateFormatted(oldPattern = YEAR_MONTH_DAY_T_TIME, newPattern = DAY_MONTH_YEAR)?:""
    }
}

const val PACKAGING = "Packaging"