package com.example.pussies.base.data.network.model

import com.google.gson.annotations.SerializedName

data class PussyDto(
    @SerializedName("breeds") val breedInfo: List<BreedInfoDto>,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)

