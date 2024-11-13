package com.example.pussies.data.network.model

import com.google.gson.annotations.SerializedName

data class PussyDto(
    @SerializedName("breeds") val breedInfo: ArrayList<BreedInfoDto>,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)

