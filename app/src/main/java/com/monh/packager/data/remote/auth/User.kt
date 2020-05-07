package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("packager")
    val packager: Packager?
)
data class Packager(
    @SerializedName("id")
    val id: Int,
    @SerializedName("seller_id")
    val sellerId: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("status")
    val status: Int
)