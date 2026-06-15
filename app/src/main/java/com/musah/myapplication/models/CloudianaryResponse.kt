package com.musah.myapplication.models

import com.google.gson.annotations.SerializedName

data class CloudianaryResponse(
    @SerializedName("url")
    val url: String,
    @SerializedName("secure_url")
    val secureUrl: String,
    @SerializedName("public_id")
    val publicId: String
)
