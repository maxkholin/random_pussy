package com.example.pussies.data.network.model

import com.google.gson.annotations.SerializedName

data class WeightDto(
    @SerializedName("metric") val metric: String? = null
)
