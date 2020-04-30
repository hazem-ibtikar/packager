package com.monh.packager.data.remote.auth


import com.google.gson.annotations.SerializedName

data class UserTokenRequest(
    @SerializedName("firebase_token")
    val firebaseToken: String?
)