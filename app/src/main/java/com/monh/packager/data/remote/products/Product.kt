package com.monh.packager.data.remote.products


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("category_id")
    val categoryId: String?,
    @SerializedName("category_name")
    val categoryName: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("bar_code")
    val barCode: String?,
    @SerializedName("is_found")
    var isFound: Boolean?,
    @SerializedName("is_not_found")
    var isNotFound: Boolean?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("quantity")
    val quantity: String?
) : Parcelable{
    fun isLocationDisplayed():Boolean{
        return !location.isNullOrBlank()
    }
}