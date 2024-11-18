package com.example.pussies.data.network

import com.example.pussies.data.network.model.PussyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(URL)
    suspend fun loadPussyData(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_BREEDS) breeds: String = getBreedsForSearch()
    ): List<PussyDto>

    companion object {
        private const val URL = "search"
        private const val QUERY_PARAM_BREEDS = "breed_ids"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val API_KEY =
            "live_qHUFyn3OEOZ8SOi9ekebI9qC6OJegiqr678qLTcuLeMTIzSMuXKxhGLMLXfnbd5n"

        private fun getBreedsForSearch(): String {
            val breeds = listOf(
                "bsho",
                "beng", "acur",
                "abys", "sfol", "nebe", "tang", "ragd", "raga",
                "lape", "sava", "lihu", "orie", "munc"
            )
            return breeds.joinToString(",")
        }
    }
}
