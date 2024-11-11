package com.example.pussies.data.mapper

import com.example.pussies.data.network.model.PussyDataDto
import com.example.pussies.domain.Pussy

class PussyMapper {

    fun mapDtoToDomain(pussy: PussyDataDto): Pussy {
        val imageUrl = pussy.url
        val breed = pussy.breedInfo.firstOrNull()

        return Pussy(
            imageUrl = imageUrl,
            weight = breed?.weight?.metric ?: UNKNOWN,
            breedName = breed?.breedName ?: UNKNOWN,
            temperament = breed?.temperament ?: UNKNOWN,
            origin = breed?.origin ?: UNKNOWN,
            description = breed?.description ?: UNKNOWN,
            lifeSpan = breed?.lifeSpan ?: UNKNOWN,
            indoor = breed?.indoor?.toString() ?: UNKNOWN,
            adaptability = breed?.adaptability?.toString() ?: UNKNOWN,
            affectionLevel = breed?.affectionLevel?.toString() ?: UNKNOWN,
            childFriendly = breed?.childFriendly?.toString() ?: UNKNOWN,
            catFriendly = breed?.catFriendly?.toString() ?: UNKNOWN,
            dogFriendly = breed?.dogFriendly?.toString() ?: UNKNOWN,
            energyLevel = breed?.energyLevel?.toString() ?: UNKNOWN,
            healthIssues = breed?.healthIssues?.toString() ?: UNKNOWN,
            intelligence = breed?.intelligence?.toString() ?: UNKNOWN,
            socialNeeds = breed?.socialNeeds?.toString() ?: UNKNOWN,
            strangerFriendly = breed?.strangerFriendly?.toString() ?: UNKNOWN,
            vocalisation = breed?.vocalisation?.toString() ?: UNKNOWN,
            wikipediaUrl = breed?.wikipediaUrl ?: UNKNOWN,
            hypoallergenic = breed?.hypoallergenic?.toString() ?: UNKNOWN
        )
    }

    companion object {

        private const val UNKNOWN = "Unknown"
    }

}