package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class UpdateStatusRequest(
    @SerializedName("status")
    val isWorking: Boolean
)