package com.monh.packager.data.remote.products


import com.google.gson.annotations.SerializedName

data class StartOrderResponse(
    @SerializedName("available")
    val available: Boolean?
)