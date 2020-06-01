package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("packager")
    var packager: Packager?
)
data class Packager(
    @SerializedName("id")
    val id: Int,
    @SerializedName("seller_id")
    val sellerId: Int,
    @SerializedName("name")
    var name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("image_url")
    var imageUrl: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("status")
    val status: Int
)