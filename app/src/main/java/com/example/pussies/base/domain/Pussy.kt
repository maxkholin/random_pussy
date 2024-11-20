package com.example.pussies.base.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pussy(
    val id: String,
    var isFavorite: Boolean,
    val imageUrl: String,
    val description: String,
    val weight: String,
    val breedName: String,
    val temperament: String,
    val origin: String,
    val lifeSpan: String,
    val indoor: String,
    val adaptability: String,
    val affectionLevel: String,
    val childFriendly: String,
    val catFriendly: String,
    val dogFriendly: String,
    val energyLevel: String,
    val healthIssues: String,
    val intelligence: String,
    val socialNeeds: String,
    val strangerFriendly: String,
    val vocalisation: String,
    val wikipediaUrl: String,
    val hypoallergenic: String
) : Parcelable

