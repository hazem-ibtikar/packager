package com.monh.packager.utils


import com.google.gson.annotations.SerializedName

data class InformativeResponse(
    @SerializedName("message")
    val message: String?
)