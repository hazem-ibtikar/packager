package com.monh.packager.data.remote.products


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("category_id")
    val categoryId: String?,
    @SerializedName("category_name")
    val categoryName: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("is_found")
    val isFound: Boolean?,
    @SerializedName("is_not_found")
    val isNotFound: Boolean?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("quantity")
    val quantity: String?
)