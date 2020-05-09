package com.monh.packager.data.remote.products


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("order_item_id")
    val order_item_id:Int,
    @SerializedName("found_count")
    val foodCount:Int?,
    @SerializedName("total_price")
    val totalPrice: String?,
    @SerializedName("product_id")
    val id: String?,
    @SerializedName("unit_price")
    val unitPrice: String?,
    @SerializedName("is_found")
    val is_found:Int,
    @SerializedName("quantity")
    val quantity: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("long_description")
    val longDescription: String?,
    @SerializedName("barcode")
    val barCode: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("parent_category_id")
    val categoryId: String?,
    @SerializedName("category_name")
    val categoryName: String?,
    @SerializedName("is_found1")
    var isFound: Boolean?,
    @SerializedName("is_not_found")
    var isNotFound: Boolean?,
    @SerializedName("display_address")
    val location: String?
) : Parcelable{
    fun isLocationDisplayed():Boolean{
        return !location.isNullOrBlank()
    }
}