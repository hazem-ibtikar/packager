package com.monh.packager.data.remote.orders


import com.google.gson.annotations.SerializedName

data class OrderPackagedResponse(
    @SerializedName("base64Pdf")
    val base64Pdf: String?,
    @SerializedName("message")
    val message: String?
)