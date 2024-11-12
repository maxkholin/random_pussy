package com.example.pussies.data.mapper

import com.example.pussies.data.database.PussyDbModel
import com.example.pussies.data.network.model.PussyDto
import com.example.pussies.domain.Pussy
import javax.inject.Inject

class PussyMapper @Inject constructor() {

    fun mapDtoToDomain(pussyDto: PussyDto, isFavorite: Boolean): Pussy {
        val id = pussyDto.id
        val imageUrl = pussyDto.url
        val breed = pussyDto.breedInfo.firstOrNull()

        return Pussy(
            id = id,
            isFavorite = isFavorite,
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

    fun mapDomainToDb(pussy: Pussy): PussyDbModel {
        return PussyDbModel(
            id = pussy.id,
            imageUrl = pussy.imageUrl,
            weight = pussy.weight,
            breedName = pussy.breedName,
            temperament = pussy.temperament,
            origin = pussy.origin,
            description = pussy.description,
            lifeSpan = pussy.lifeSpan,
            indoor = pussy.indoor,
            adaptability = pussy.adaptability,
            affectionLevel = pussy.affectionLevel,
            childFriendly = pussy.childFriendly,
            catFriendly = pussy.catFriendly,
            dogFriendly = pussy.dogFriendly,
            energyLevel = pussy.energyLevel,
            healthIssues = pussy.healthIssues,
            intelligence = pussy.intelligence,
            socialNeeds = pussy.socialNeeds,
            strangerFriendly = pussy.strangerFriendly,
            vocalisation = pussy.vocalisation,
            wikipediaUrl = pussy.wikipediaUrl,
            hypoallergenic = pussy.hypoallergenic
        )
    }

    fun mapDbToDomain(pussyDbModel: PussyDbModel): Pussy {
        return Pussy(
            id = pussyDbModel.id,
            isFavorite = true,
            imageUrl = pussyDbModel.imageUrl,
            weight = pussyDbModel.weight,
            breedName = pussyDbModel.breedName,
            temperament = pussyDbModel.temperament,
            origin = pussyDbModel.origin,
            description = pussyDbModel.description,
            lifeSpan = pussyDbModel.lifeSpan,
            indoor = pussyDbModel.indoor,
            adaptability = pussyDbModel.adaptability,
            affectionLevel = pussyDbModel.affectionLevel,
            childFriendly = pussyDbModel.childFriendly,
            catFriendly = pussyDbModel.catFriendly,
            dogFriendly = pussyDbModel.dogFriendly,
            energyLevel = pussyDbModel.energyLevel,
            healthIssues = pussyDbModel.healthIssues,
            intelligence = pussyDbModel.intelligence,
            socialNeeds = pussyDbModel.socialNeeds,
            strangerFriendly = pussyDbModel.strangerFriendly,
            vocalisation = pussyDbModel.vocalisation,
            wikipediaUrl = pussyDbModel.wikipediaUrl,
            hypoallergenic = pussyDbModel.hypoallergenic
        )
    }

    fun mapListDbToDomain(list: List<PussyDbModel>): List<Pussy> {
        return list.map {
            mapDbToDomain(it)
        }
    }

    companion object {

        private const val UNKNOWN = "Unknown"
    }

}