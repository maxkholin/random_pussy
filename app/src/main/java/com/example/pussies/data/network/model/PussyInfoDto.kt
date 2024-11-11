package com.example.pussies.data.network.model

import com.google.gson.annotations.SerializedName

data class PussyInfoDto(
    @SerializedName("breeds") val breedInfo: ArrayList<BreedInfoDto>,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)

