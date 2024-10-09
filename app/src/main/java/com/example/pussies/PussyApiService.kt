package com.example.pussies

import retrofit2.http.GET
import retrofit2.http.Query

const val URL = "search?api_key=live_qHUFyn3OEOZ8SOi9ekebI9qC6OJegiqr678qLTcuLeMTIzSMuXKxhGLMLXfnbd5n"


interface PussyApiService {
    @GET(URL)
    suspend fun loadPussyData(@Query("breed_ids") breed: String): List<Pussy>
}
