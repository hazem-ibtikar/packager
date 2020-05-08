package com.monh.packager.data.remote.seller


import com.google.gson.annotations.SerializedName

data class ContactUsResponse(
    @SerializedName("contacts")
    val contacts: Contacts?
) {
    data class Contacts(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("latitude")
        val latitude: String?,
        @SerializedName("longitude")
        val longitude: String?,
        @SerializedName("support_address")
        val supportAddress: String?,
        @SerializedName("support_email")
        val supportEmail: String?,
        @SerializedName("support_phoneno")
        val supportPhoneno: String?,
        @SerializedName("user_id")
        val userId: Int?,
        @SerializedName("website")
        val website: String?
    )
}