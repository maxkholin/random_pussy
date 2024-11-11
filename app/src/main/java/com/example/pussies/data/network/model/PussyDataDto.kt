package com.example.pussies.data.network.model

import com.google.gson.annotations.SerializedName

data class PussyDataDto(
    @SerializedName("breeds") var breedInfo: ArrayList<BreedInfoDto>,
    @SerializedName("url") val url: String
)

