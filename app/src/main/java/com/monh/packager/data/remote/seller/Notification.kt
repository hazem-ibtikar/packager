package com.monh.packager.data.remote.seller


import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("time")
    val time: String?
)