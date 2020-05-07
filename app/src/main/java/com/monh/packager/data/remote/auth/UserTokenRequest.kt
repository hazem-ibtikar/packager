package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class UserTokenRequest(
    @SerializedName("device_uuid")
    val deviceUuid: String? ,
    @SerializedName("lang")
    val lang: String?,
    @SerializedName("firebase_token")
    val fb_token: String?
)