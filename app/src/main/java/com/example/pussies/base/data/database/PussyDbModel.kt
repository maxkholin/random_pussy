package com.example.pussies.base.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pussies")
data class PussyDbModel(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val weight: String,
    val breedName: String,
    val temperament: String,
    val origin: String,
    val description: String,
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
)

