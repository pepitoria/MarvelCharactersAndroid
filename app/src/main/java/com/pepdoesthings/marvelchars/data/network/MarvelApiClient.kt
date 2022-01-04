package com.pepdoesthings.marvelchars.data.network

import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiClient {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CharactersResponse>


    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Path("characterId") characterId: String
    ): Response<String>
}