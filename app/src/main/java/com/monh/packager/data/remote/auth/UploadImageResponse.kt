package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("ref_image")
    val refImage: String?
)