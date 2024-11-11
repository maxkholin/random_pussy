package com.example.pussies.data.network

import com.example.pussies.domain.Pussy
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(URL)
    suspend fun loadPussyData(@Query("breed_ids") breed: String): List<Pussy>

    companion object {

        private const val URL = "search?api_key=live_qHUFyn3OEOZ8SOi9ekebI9qC6OJegiqr678qLTcuLeMTIzSMuXKxhGLMLXfnbd5n"
    }
}
