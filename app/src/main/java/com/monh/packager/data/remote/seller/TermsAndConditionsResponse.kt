package com.monh.packager.data.remote.seller


import com.google.gson.annotations.SerializedName

data class TermsAndConditionsResponse(
    @SerializedName("terms")
    val terms: Terms?
) {
    data class Terms(
        @SerializedName("address")
        val address: Any?,
        @SerializedName("application_name")
        val applicationName: Any?,
        @SerializedName("company_name")
        val companyName: String?,
        @SerializedName("country")
        val country: Any?,
        @SerializedName("date_of_establishment")
        val dateOfEstablishment: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("is_active")
        val isActive: Int?,
        @SerializedName("template_data")
        val templateData: String?,
        @SerializedName("tnc_type")
        val tncType: Int?,
        @SerializedName("tnc_user_link")
        val tncUserLink: Any?,
        @SerializedName("user_id")
        val userId: Int?,
        @SerializedName("website_name")
        val websiteName: String?
    )
}