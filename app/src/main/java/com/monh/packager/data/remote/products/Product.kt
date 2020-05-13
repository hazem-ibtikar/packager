package com.monh.packager.data.remote.products


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("order_item_id")
    val order_item_id:Int,
    @SerializedName("found_count")
    var foundCount:Int,
    @SerializedName("total_price")
    val totalPrice: String?,
    @SerializedName("product_id")
    val id: Int?,
    @SerializedName("unit_price")
    val unitPrice: String?,
    @SerializedName("is_found")
    var isFound:Int,
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
    @SerializedName("display_address")
    val location: String?,
    @SerializedName("unit")
    val unit: String?
) : Parcelable{
    fun isLocationDisplayed():Boolean{
        return !location.isNullOrBlank()
    }

    fun isAdded() = isFound == 1 && foundCount > 0

    fun isNotFound() = isFound == 0
    fun prepareForEdit() {
        isFound = 1
        foundCount = 0
    }

    fun setAsNotFound(){
        isFound = 0
    }

    fun hasUnit():Boolean{
        return !unit.isNullOrBlank() && unit != "null"
    }
    fun isHandledByPackager():Boolean{
        return isAdded() || isNotFound()
    }
}