package com.example.a20230109nelsonmartineznycschools.model


import com.google.gson.annotations.SerializedName

/**
 * Use of kotlin data class as to avoid getters and setters.
 */
data class Schools(
    @SerializedName("city")
    val city: String?,
    @SerializedName("dbn")
    val dbn: String?,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String?,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("school_accessibility_description")
    val schoolAccessibilityDescription: String?,
    @SerializedName("school_email")
    val schoolEmail: String?,
    @SerializedName("school_name")
    val schoolName: String?,
    @SerializedName("state_code")
    val stateCode: String?,
    @SerializedName("subway")
    val subway: String?,
    @SerializedName("total_students")
    val totalStudents: String?,
    @SerializedName("transfer")
    val transfer: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("zip")
    val zip: String?
)