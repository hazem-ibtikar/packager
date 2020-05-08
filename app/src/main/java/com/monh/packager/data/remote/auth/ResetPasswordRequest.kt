package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("email")
    val email: String?
)