package com.example.pussies.base.data

import com.example.pussies.base.data.database.PussyDbModel
import com.example.pussies.base.data.mapper.PussyMapper
import com.example.pussies.base.data.network.model.PussyDto
import com.example.pussies.base.data.network.model.BreedInfoDto
import com.example.pussies.base.data.network.model.WeightDto
import com.example.pussies.base.domain.Pussy
import org.junit.Assert.assertEquals
import org.junit.Test

class PussyMapperTest {

    private val mapper = PussyMapper()

    @Test
    fun `mapDtoToDomain should correctly map PussyDto to Pussy`() {

        val breed = BreedInfoDto(
            weight = WeightDto("4 - 5"),
            breedName = "Persian",
            temperament = "Calm",
            origin = "Iran",
            description = "A calm and friendly cat.",
            lifeSpan = "10 - 15 years",
            indoor = 1,
            adaptability = 5,
            affectionLevel = 5,
            childFriendly = 4,
            catFriendly = 4,
            dogFriendly = 3,
            energyLevel = 3,
            healthIssues = 2,
            intelligence = 3,
            socialNeeds = 4,
            strangerFriendly = 4,
            vocalisation = 2,
            wikipediaUrl = "https://example.com",
            hypoallergenic = 0
        )
        val dto = PussyDto(
            id = "123",
            url = "https://example.com/image.jpg",
            breedInfo = listOf(breed)
        )

        val result = mapper.mapDtoToDomain(dto, isFavorite = true)

        assertEquals("123", result.id)
        assertEquals(true, result.isFavorite)
        assertEquals("https://example.com/image.jpg", result.imageUrl)
        assertEquals("4 - 5", result.weight)
        assertEquals("Persian", result.breedName)
        assertEquals("Calm", result.temperament)
        assertEquals("Iran", result.origin)
        assertEquals("A calm and friendly cat.", result.description)
        assertEquals("10 - 15 years", result.lifeSpan)
        assertEquals("1", result.indoor)
        assertEquals("5", result.adaptability)
        assertEquals("5", result.affectionLevel)
        assertEquals("4", result.childFriendly)
        assertEquals("4", result.catFriendly)
        assertEquals("3", result.dogFriendly)
        assertEquals("3", result.energyLevel)
        assertEquals("2", result.healthIssues)
        assertEquals("3", result.intelligence)
        assertEquals("4", result.socialNeeds)
        assertEquals("4", result.strangerFriendly)
        assertEquals("2", result.vocalisation)
        assertEquals("https://example.com", result.wikipediaUrl)
        assertEquals("0", result.hypoallergenic)
    }

    @Test
    fun `mapDomainToDb should correctly map Pussy to PussyDbModel`() {

        val pussy = Pussy(
            id = "123",
            isFavorite = true,
            imageUrl = "https://example.com/image.jpg",
            weight = "4 - 5",
            breedName = "Persian",
            temperament = "Calm",
            origin = "Iran",
            description = "A calm and friendly cat.",
            lifeSpan = "10 - 15 years",
            indoor = "1",
            adaptability = "5",
            affectionLevel = "5",
            childFriendly = "4",
            catFriendly = "4",
            dogFriendly = "3",
            energyLevel = "3",
            healthIssues = "2",
            intelligence = "3",
            socialNeeds = "4",
            strangerFriendly = "4",
            vocalisation = "2",
            wikipediaUrl = "https://example.com",
            hypoallergenic = "0"
        )

        val result = mapper.mapDomainToDb(pussy)

        assertEquals("123", result.id)
        assertEquals("https://example.com/image.jpg", result.imageUrl)
        assertEquals("4 - 5", result.weight)
        assertEquals("Persian", result.breedName)
        assertEquals("Calm", result.temperament)
        assertEquals("Iran", result.origin)
        assertEquals("A calm and friendly cat.", result.description)
        assertEquals("10 - 15 years", result.lifeSpan)
        assertEquals("1", result.indoor)
        assertEquals("5", result.adaptability)
        assertEquals("5", result.affectionLevel)
        assertEquals("4", result.childFriendly)
        assertEquals("4", result.catFriendly)
        assertEquals("3", result.dogFriendly)
        assertEquals("3", result.energyLevel)
        assertEquals("2", result.healthIssues)
        assertEquals("3", result.intelligence)
        assertEquals("4", result.socialNeeds)
        assertEquals("4", result.strangerFriendly)
        assertEquals("2", result.vocalisation)
        assertEquals("https://example.com", result.wikipediaUrl)
        assertEquals("0", result.hypoallergenic)
    }

    @Test
    fun `mapDbToDomain should correctly map PussyDbModel to Pussy`() {

        val dbModel = PussyDbModel(
            id = "123",
            imageUrl = "https://example.com/image.jpg",
            weight = "4 - 5",
            breedName = "Persian",
            temperament = "Calm",
            origin = "Iran",
            description = "A calm and friendly cat.",
            lifeSpan = "10 - 15 years",
            indoor = "1",
            adaptability = "5",
            affectionLevel = "5",
            childFriendly = "4",
            catFriendly = "4",
            dogFriendly = "3",
            energyLevel = "3",
            healthIssues = "2",
            intelligence = "3",
            socialNeeds = "4",
            strangerFriendly = "4",
            vocalisation = "2",
            wikipediaUrl = "https://example.com",
            hypoallergenic = "0"
        )

        val result = mapper.mapDbToDomain(dbModel)

        assertEquals("123", result.id)
        assertEquals(true, result.isFavorite)
        assertEquals("https://example.com/image.jpg", result.imageUrl)
        assertEquals("4 - 5", result.weight)
        assertEquals("Persian", result.breedName)
        assertEquals("Calm", result.temperament)
        assertEquals("Iran", result.origin)
        assertEquals("A calm and friendly cat.", result.description)
        assertEquals("10 - 15 years", result.lifeSpan)
        assertEquals("1", result.indoor)
        assertEquals("5", result.adaptability)
        assertEquals("5", result.affectionLevel)
        assertEquals("4", result.childFriendly)
        assertEquals("4", result.catFriendly)
        assertEquals("3", result.dogFriendly)
        assertEquals("3", result.energyLevel)
        assertEquals("2", result.healthIssues)
        assertEquals("3", result.intelligence)
        assertEquals("4", result.socialNeeds)
        assertEquals("4", result.strangerFriendly)
        assertEquals("2", result.vocalisation)
        assertEquals("https://example.com", result.wikipediaUrl)
        assertEquals("0", result.hypoallergenic)
    }
}
