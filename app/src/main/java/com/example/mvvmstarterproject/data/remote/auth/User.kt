package com.example.mvvmstarterproject.data.remote.auth


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("arabic_full_name")
    val arabicFullName: String?,
    @SerializedName("contry_code")
    val contryCode: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("english_full_name")
    val englishFullName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("phone")
    val phone: String?
)