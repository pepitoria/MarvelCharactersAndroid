package com.pepdoesthings.marvelchars.data.repository

import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import com.pepdoesthings.marvelchars.data.network.MarvelService
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelService
) : MarvelRepository {

    override suspend fun getCharacters(search: String): CharactersResponse? {
        return api.getCharacters(search)
    }

    override suspend fun getCharacterDetail(charId: Long): CharactersResponse? {
        return api.getCharacterDetail(charId)
    }
}