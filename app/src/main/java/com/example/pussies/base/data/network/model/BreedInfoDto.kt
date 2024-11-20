package com.example.pussies.base.data.network.model

import com.google.gson.annotations.SerializedName

data class BreedInfoDto(
    @SerializedName("weight") var weight: WeightDto? = null,
    @SerializedName("name") var breedName: String? = null,
    @SerializedName("temperament") var temperament: String? = null,
    @SerializedName("origin") var origin: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("life_span") var lifeSpan: String? = null,
    @SerializedName("indoor") var indoor: Int? = null,
    @SerializedName("adaptability") var adaptability: Int? = null,
    @SerializedName("affection_level") var affectionLevel: Int? = null,
    @SerializedName("child_friendly") var childFriendly: Int? = null,
    @SerializedName("cat_friendly") var catFriendly: Int? = null,
    @SerializedName("dog_friendly") var dogFriendly: Int? = null,
    @SerializedName("energy_level") var energyLevel: Int? = null,
    @SerializedName("health_issues") var healthIssues: Int? = null,
    @SerializedName("intelligence") var intelligence: Int? = null,
    @SerializedName("social_needs") var socialNeeds: Int? = null,
    @SerializedName("stranger_friendly") var strangerFriendly: Int? = null,
    @SerializedName("vocalisation") var vocalisation: Int? = null,
    @SerializedName("wikipedia_url") var wikipediaUrl: String? = null,
    @SerializedName("hypoallergenic") var hypoallergenic: Int? = null
)